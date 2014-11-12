#include <map>
#include <queue>
#include "graph.h"
#include "parser.h"

/* Inspirations
 * http://stackoverflow.com/questions/11429308/how-to-check-if-value-is-in-list
 * http://www.cplusplus.com/reference/algorithm/find/
 * http://www.cplusplus.com/reference/utility/pair/
 * http://www.cplusplus.com/reference/queue/queue/
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

list< vector<int> > greedyChains(Graph& graph) {

    list< vector<int> > chains = {};
    vector<int> c = longuestChain(graph);

    while (!c.empty()) {
        // Remove all vertices in c and incident arcs from graph
        for (int& v : c) {
            graph - v;
        }
        std::cout << "ping" << std::endl;
        chains.push_back(c);
        c = longuestChain(graph);
    }

    for (int& v : graph.GetVertices()) {
        chains.push_back(vector<int> (1, v));
    }

    return chains;
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
int topologicSortCount(Graph& graph) {

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

int main()
{ 
    vector< pair <int, int> > axis;
    list< vector<int> > chains;
    axis.push_back(std::make_pair(1,3));
    axis.push_back(std::make_pair(2,3));
    axis.push_back(std::make_pair(2,0));
    Graph graph (4, axis);
    std::cout << topologicSortCount(graph) << std::endl;

    std::cout << "Greedy algorithm :" << std::endl;
    chains = greedyChains(graph);

    for (auto& c : chains) {
        std::cout << "(";
        for (int& v : c) {
            std::cout << v << ",";
        }
        std::cout << ")" << std::endl;
    }

    Parser parser;
    Graph graph2 = parser.Read("/home/gwaihir/Documents/My Documents/INF4715_ALGO/AlgoLab2-build/tp2-donnees/poset10-4a");
    std::cout << topologicSortCount(graph2) << std::endl;
    chains = {};

    return 0;
}

