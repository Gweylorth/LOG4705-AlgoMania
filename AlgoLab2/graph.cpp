#include "graph.h"

Graph::Graph(int v, vector<pair<int, int> > &a)
    : vertices(v), axis(a) {
    this->adjacencies = vector < list <int> > (this->vertices);

    for (auto& ax : this->axis) {
        this->adjacencies[ax.first].push_back(ax.second);
    }
}

int Graph::InDegree(int v) {
    int count = 0;

    for (auto& adj : this->adjacencies) {
        if (std::find(adj.begin(), adj.end(), v) != adj.end()) {
            count++;
        }
    }

    return count;
}
