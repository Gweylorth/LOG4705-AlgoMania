#include <queue>
#include "graph.h"

/* Inspirations
 * http://stackoverflow.com/questions/11429308/how-to-check-if-value-is-in-list
 * http://www.cplusplus.com/reference/algorithm/find/
 * http://www.cplusplus.com/reference/utility/pair/
 * http://www.cplusplus.com/reference/queue/queue/
 */

using std::queue;

vector<int> longuestChain(Graph& graph) {
    vector<int> c, pred (graph.GetVertices(), -1);
    vector<bool> visited (graph.GetVertices(), false);
    queue<int> q;

    for (int v = 0; v < graph.GetVertices(); v++) {
        std::cout << v << " in degree : " << graph.InDegree(v) << std::endl;
        if (graph.InDegree(v) == 0) {
            q.push(v);
        }
    }

    int last = -1;

    while (!q.empty()) {
        int u = q.back();
        q.pop();
        for (auto& v : graph.adjacencies[u]) { // for each (u,v) axis
            pred[v] = u;
            last = v;
            if (!visited[v]){
                q.push(v);
                visited[v] = true;
            }
        }
    }

    while (last != -1) {
        c.push_back(last);
        last = pred[last];
    }

    return c;
}

int main()
{ 
    vector< pair <int, int> > axis;
    axis.push_back(std::make_pair(1,3));
    axis.push_back(std::make_pair(2,3));
    axis.push_back(std::make_pair(2,0));
    Graph graph (4, axis);

    std::cout << "Graph size : " << graph.adjacencies.size() << std::endl;

    vector<int> chain1 = longuestChain(graph);

    std::cout << "Greedy algorithm : (";

    for (int& v : chain1) {
        std::cout << v << ",";
    }

    std::cout << ")" << std::endl;

    return 0;
}

