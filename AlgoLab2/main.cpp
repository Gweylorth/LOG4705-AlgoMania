#include <queue>
#include "graph.h"

/* Inspirations
 * http://stackoverflow.com/questions/11429308/how-to-check-if-value-is-in-list
 * http://www.cplusplus.com/reference/algorithm/find/
 * http://www.cplusplus.com/reference/utility/pair/
 * http://www.cplusplus.com/reference/queue/queue/
 */

using std::queue;

void longuestChains(Graph& graph, list< vector<int> >& chains) {

    if (graph.GetVertices().size() == 0) {
        return;
    }

    vector<int> pred (graph.GetVertices().size(), -1);
    vector<int> q; // use as a queue that can be iterated through

    std::cout << "herp" << std::endl;

    for (int& v : graph.GetVertices()) {
        std::cout << v << " in degree : " << graph.InDegree(v) << std::endl;
        if (graph.InDegree(v) == 0) {
            q.push_back(v);
        }
    }

    std::cout << "derp" << std::endl;

    int last = -1;

    while (!q.empty()) {
        int u = q.back();
        q.pop_back();
        for (auto& v : graph.adjacencies[u]) { // for each (u,v) axis
            pred[v] = u;
            last = v;
            std::vector<int>::iterator it = std::find(q.begin(), q.end(), v);
            if(it != q.end()) {
                std::rotate(it, it + 1, q.end());
                std::cout << "derp" << std::endl;

            } else {
                q.push_back(v);
            }

        }
    }

    std::cout << "hurpa" << std::endl;

    vector<int> c;
    while (last != -1) {
        c.push_back(last);
        last = pred[last];
    }

    std::cout << "coin" << std::endl;
    chains.push_back(c);
    std::cout << "coincoin" << std::endl;
    for (int& v : c) {
        graph - v;
    }

    longuestChains(graph, chains);
}

int main()
{ 
    vector< pair <int, int> > axis;
    axis.push_back(std::make_pair(1,3));
    axis.push_back(std::make_pair(2,3));
    axis.push_back(std::make_pair(2,0));
    Graph graph (4, axis);

    std::cout << "Graph size : " << graph.adjacencies.size() << std::endl;

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

    return 0;
}

