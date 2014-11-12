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

// Recursivly compute chains in the graph, from longuest to shortest,
// and stores them in a list
void longuestChains(Graph& graph, list< vector<int> >& chains) {

    //If we're done with this graph, stop
    if (graph.GetVertices().size() == 0) {
        return;
    }

    std::map<int,int> pred;
    for (int v : graph.GetVertices()) {
        pred[v] = -1;
    }

    vector<int> q; // use as a queue that can be iterated through

    // Get all vertices with no predecessor
    for (int& v : graph.GetVertices()) {
        if (graph.InDegree(v) == 0) {
            q.push_back(v);
        }
    }

    int last = -1;

    // Handle every successor to each vertex in the queue
    while (!q.empty()) {
        int u = q.back();
        q.pop_back();
        for (auto& v : graph.adjacencies[u]) { // for each (u,v) axis
            pred[v] = u;
            last = v;
            std::vector<int>::iterator it = std::find(q.begin(), q.end(), v);
            if(it != q.end()) {
                std::rotate(it, it + 1, q.end());
            } else {
                q.push_back(v);
            }
        }
    }

    // Construct the chain and add it to the list
    vector<int> c;
    while (last != -1) {
        c.push_back(last);
        last = pred[last];
    }
    std::reverse(c.begin(), c.end());
    chains.push_back(c);

    // Remove current vertices from the graph for the next call
    for (int& v : c) {
        graph - v;
    }
    longuestChains(graph, chains);
}

void topologicSort(Graph& graph) {

    queue<int> q; // use as a queue that can be iterated through
    vector<int> degree (graph.GetVertices().size(), -1);

    // Get all vertices with no predecessor
    for (int& v : graph.GetVertices()) {
        degree[v] = graph.InDegree(v);
        if (degree[v] == 0) {
            q.push(v);
        }
    }

    while(!q.empty()) {
        int u = q.front();
        q.pop();
        std::cout << u << std::endl;
        for (auto& v : graph.adjacencies[u]) { // for each (u,v) axis
            degree[v]--;
            if (degree[v] == 0) {
                q.push(v);
            }
        }
    }
}

int main()
{ 
    vector< pair <int, int> > axis;
    axis.push_back(std::make_pair(1,3));
    axis.push_back(std::make_pair(2,3));
    axis.push_back(std::make_pair(2,0));
    Graph graph (4, axis);
    topologicSort(graph);

    list< vector<int> > chains;
    longuestChains(graph, chains);

    std::cout << "Greedy algorithm :" <<std::endl;

    for (auto& c : chains) {
        std::cout << "(";
        for (int& v : c) {
            std::cout << v << ",";
        }
        std::cout << ")" << std::endl;
    }

    Parser parser;
    Graph graph2 = parser.Read("/home/gwaihir/Documents/My Documents/INF4715_ALGO/AlgoLab2-build/tp2-donnees/poset10-4a");
    topologicSort(graph2);
    chains = {};
    longuestChains(graph2, chains);

    return 0;
}

