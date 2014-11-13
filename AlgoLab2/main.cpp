#include <map>
#include <queue>
#include <cmath>
#include "graph.h"
#include "parser.h"

/* Inspirations
 * http://stackoverflow.com/questions/11429308/how-to-check-if-value-is-in-list
 * http://www.cplusplus.com/reference/algorithm/find/
 * http://www.cplusplus.com/reference/utility/pair/
 */

using std::queue;

vector<int> longuestChain(Graph& graph) {

    std::map<int,int> pred;
    list<int> q; // use as a queue that can be iterated through
    int last = -1;

    for (int& v : graph.GetVertices()) {
        pred[v] = -1;
        // Get all vertices with no predecessor
        if (graph.InDegree(v) == 0) {
            q.push_front(v);
        }
    }

    // Handle every successor to each vertex in the queue
    while (!q.empty()) {
        int u = q.back();
        q.pop_back();
        last = u;
        for (auto& v : graph.adjacencies[u]) { // for each (u,v) axis
            pred[v] = u;
            std::list<int>::iterator it = std::find(q.begin(), q.end(), v);
            if(it != q.end()) {
                q.erase(it);
            }
            q.push_front(v);
        }
    }

    // Construct the chain and add it to the list
    vector<int> c = {};
    while (last != -1) {
        c.push_back(last);
        last = pred[last];
    }

    return c;
}

list< vector<int> > greedyChains(Graph graph) {

    list< vector<int> > chains = {};
    vector<int> c = longuestChain(graph);

    while (!c.empty()) {
        // Remove all vertices in c and incident arcs from graph
        for (int& v : c) {
            graph - v;
        }
        chains.push_back(c);
        c = longuestChain(graph);
    }

    for (int& v : graph.GetVertices()) {
        chains.push_back(vector<int> (1, v));
    }

    return chains;
}

// Applies mathematical approximation to greedy chains computation
int greedyTopologicSortCount(Graph& graph) {
    double vertices = graph.GetVertices().size();
    auto chains = greedyChains(graph);
    double hg = 0;
    for (auto& c : chains) {
        double x = c.size()/vertices;
        hg += -x * log2(x);
    }
    return pow(2, .5 * (vertices+1) * hg);
}

// Backtracking support for topologic sort count
void backtrack(Graph& graph, vector<int>& queue, vector<int>& degree, int& count) {
    if (queue.empty()) {
        count++;
        return;
    }

    for (int i = 0; i < queue.size(); i++) {
        vector<int> branchQueue (queue);
        vector<int> branchDegree (degree);

        int u = branchQueue[i];
        branchQueue.erase(branchQueue.begin() + i);

        for (auto& v : graph.adjacencies[u]) { // for each (u,v) axis
            branchDegree[v]--;
            if (branchDegree[v] == 0) {
                branchQueue.push_back(v);
            }
        }
        backtrack(graph, branchQueue, branchDegree, count);
    }
}

// Returns number of topologic sort permutations through backtracking
int backtrackingTopologicSortCount(Graph& graph) {

    vector<int> q; // use as a queue that can be iterated through
    vector<int> degree (graph.GetVertices().size(), -1);
    int count = 0;

    // Get all vertices with no predecessor
    for (int& v : graph.GetVertices()) {
        degree[v] = graph.InDegree(v);
        if (degree[v] == 0) {
            q.push_back(v);
        }
    }

    backtrack(graph, q, degree, count);

    return count;
}

// Recursive call for dynamic algorithm
int dynamicRecursive(list< vector<int> >& chains, vector<int>& extensions, int index) {

    // if we already did this one, return it
    if (extensions[index] != 0) {
        return extensions[index];
    }

    // else, compute it recursively from its neighbours
    int sum = 0;
    for (auto& c : chains) {
        sum += dynamicRecursive(chains, extensions, c.size());
    }

    // update extensions with new value and return it
    extensions[index] = sum;
    return sum;
}

int dynamicTopologicSortCount(Graph& graph) {
    auto chains = greedyChains(graph);
    // we need a n-dimentional array, depending on the number of chains found
    int tabSize = 1;
    for (auto& c : chains) {
        tabSize *= c.size() + 1;
    }
    vector<int> extensions (tabSize, 0);

    // initialize #P 0,0,..,0 to 1
    for (auto& c1 : chains) {
        for (auto& c2 : chains) {
            extensions[c1.size() * c2.size()] = 1;
        }
    }

    return dynamicRecursive(chains, extensions, tabSize - 1);
}

// Prints a list of chains to stdout
void printChains (list< vector<int> >& chains) {
    std::string out = "";
    for (auto& c : chains) {
        out.append("{");
        for (int& v : c) {
            out.append(std::to_string(v) + " ");
        }
        out.pop_back();
        out.append("}\n");
    }
    std::cout << out << std::endl;
}

int BadUseError()
{
    std::cout << "Bad args! Usage : program [-p] -f filepath" << std::endl;
    return 1;
}

int main(int argc, char* argv[])
{
    if (argc < 3 || argc > 4) {
        return BadUseError();
    }

    bool print = false;

    Parser parser;
    Graph graph;
    int count;
    clock_t start, stop;

    for(int i = 1; i < argc; i++) {
        if (std::string(argv[i]) == "-p") {
            print = true;
        }
        else if (std::string(argv[i]) == "-f") {
            graph = parser.Read(std::string(argv[++i]));
        }
        else {
            return BadUseError();
        }
    }

    start = clock();
    count = dynamicTopologicSortCount(graph);
    stop = clock();
    std::cout << double(stop - start) / CLOCKS_PER_SEC << std::endl;
    if (print) {
        std::cout << count << std::endl;
    }

    return 0;
}

