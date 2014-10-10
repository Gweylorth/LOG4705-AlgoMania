#ifndef PARSER_H
#define PARSER_H

#include <string>
#include <fstream>
#include <cmath>
#include <sstream>
#include "matrix.h"

class Parser
{
public:
    Parser();
    Matrix Read(const std::string filePath);
};

#endif // PARSER_H
