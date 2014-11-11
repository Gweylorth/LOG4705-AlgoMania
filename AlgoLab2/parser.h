#ifndef PARSER_H
#define PARSER_H

#include <string>
#include <fstream>
#include <cmath>
#include <sstream>
#include "graph.h"

class Parser
{
public:
    Parser();
    Graph Read(const std::string filePath);
};

#endif // PARSER_H
