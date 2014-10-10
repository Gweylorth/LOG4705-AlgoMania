// Solving to INF4705 Lab1, Polytechnique Montreal
// Due 2014/10/10
// Author : Gwenegan Hudin 1756642

#include "matrix.h"
#include "parser.h"

/* Inspirations :
 * http://msdn.microsoft.com/en-us/library/hh873134.aspx
 * http://www.cplusplus.com/reference/vector/vector/
 * http://www.cplusplus.com/doc/tutorial/arrays/
 * http://www.cplusplus.com/reference/tuple/tuple/
 * http://stackoverflow.com/questions/7868936/c-read-file-line-by-line
 * Algorithmes Diviser-pour-Regner, Gilles Pesant
 */

// Computes and returns a simple naive product between matrixA and matrixB.
Matrix NaiveMult(Matrix a, Matrix b)
{
    Matrix product(a.GetRowLength());
     for (size_t i = 0; i < a.GetRowLength(); i++) {
         for (size_t j = 0; j < b.GetColLength(); j++) {
             for (size_t k = 0; k < product.GetRowLength(); k++) {
                 product[i][j] += a[i][k] * b[k][j];
             }
         }
     }

	 return product;
}

// Computes and returns the product between a and b using Strassen divide-and-conquer algorithm,
// up to threshold*threshold matrices.
Matrix StrassenMult(unsigned int threshold, Matrix a, Matrix b)
{
	Matrix m1, m2, m3, m4, m5, m6, m7, c(a.GetRowLength());
    auto aSplit = a.Split4();
    auto bSplit = b.Split4();

    // Compute the 7 submatrices, using either Naive or StrassenMult, according to threshold
    // and current matrix size.
    // m1 = (a21 + a22 − a11) × (b22 − b12 + b11)
    // m2 = a11 × b11
    // m3 = a12 × b21
    // m4 = (a11 − a21) × (b22 − b12)
    // m5 = (a21 + a22) × (b12 − b11)
    // m6 = (a12 − a21 + a11 − a22) × b22
    // m7 = a22 × (b11 + b22 − b12 − b21)
    if (std::get<0>(aSplit).GetRowLength() <= threshold) {
		m1 = NaiveMult(
            std::get<2>(aSplit) +std::get<3>(aSplit) -std::get<0>(aSplit),
			std::get<3>(bSplit) -std::get<1>(bSplit) +std::get<0>(bSplit)
			);
		m2 = NaiveMult(
			std::get<0>(aSplit),
			std::get<0>(bSplit)
			);
		m3 = NaiveMult(
			std::get<1>(aSplit),
			std::get<2>(bSplit)
			);
		m4 = NaiveMult(
			std::get<0>(aSplit) -std::get<2>(aSplit),
			std::get<3>(bSplit) -std::get<1>(bSplit)
			);
		m5 = NaiveMult(
            std::get<2>(aSplit) +std::get<3>(aSplit),
			std::get<1>(bSplit) -std::get<0>(bSplit)
			);
		m6 = NaiveMult(
			std::get<1>(aSplit) -std::get<2>(aSplit) +std::get<0>(aSplit) -std::get<3>(aSplit),
			std::get<3>(bSplit)
			);
		m7 = NaiveMult(
			std::get<3>(aSplit),
			std::get<0>(bSplit) +std::get<3>(bSplit) -std::get<1>(bSplit) -std::get<2>(bSplit)
			);
	}
	else {
		m1 = StrassenMult(
			threshold,
			std::get<2>(aSplit) +std::get<3>(aSplit) -std::get<0>(aSplit),
			std::get<3>(bSplit) -std::get<1>(bSplit) +std::get<0>(bSplit)
			);
		m2 = StrassenMult(
			threshold,
			std::get<0>(aSplit),
			std::get<0>(bSplit)
			);
		m3 = StrassenMult(
			threshold,
			std::get<1>(aSplit),
			std::get<2>(bSplit)
			);
		m4 = StrassenMult(
			threshold,
			std::get<0>(aSplit) -std::get<2>(aSplit),
			std::get<3>(bSplit) -std::get<1>(bSplit)
			);
		m5 = StrassenMult(
			threshold,
            std::get<2>(aSplit) +std::get<3>(aSplit),
			std::get<1>(bSplit) -std::get<0>(bSplit)
			);
		m6 = StrassenMult(
			threshold,
			std::get<1>(aSplit) -std::get<2>(aSplit) +std::get<0>(aSplit) -std::get<3>(aSplit),
			std::get<3>(bSplit)
			);
		m7 = StrassenMult(
			threshold,
			std::get<3>(aSplit),
			std::get<0>(bSplit) +std::get<3>(bSplit) -std::get<1>(bSplit) -std::get<2>(bSplit)
			);
	}

	Matrix c11 = m2 + m3;
	Matrix c12 = m1 + m2 + m5 + m6; 
	Matrix c21 = m1 + m2 + m4 - m7;
	Matrix c22 = m1 + m2 + m4 + m5;

    // Build a new matrix from its 4 subdivisions
	size_t splitSize = c11.GetRowLength();
	for (size_t i = 0; i < c11.GetRowLength(); i++) {
		size_t iShifted = i + splitSize;
		for (size_t j = 0; j < c11.GetColLength(); j++) {
			size_t jShifted = j + splitSize;
			c[i][j] = c11[i][j];
			c[i][jShifted] = c12[i][j];
			c[iShifted][j] = c21[i][j];
			c[iShifted][jShifted] = c22[i][j];
		}
	}

	return c;
}

int BadUseError()
{
    std::cout << "Bad args! Usage : program [-p] -f filepath1 filepath2" << std::endl;
    return 1;
}

int main(int argc, char* argv[])
{
    if (argc < 4 || argc > 5) {
        return BadUseError();
    }

    bool print = false;

    Parser parser;
    Matrix c, d, product1, product2;
    clock_t start, stop;

    for(int i = 1; i < argc; i++) {
        if (std::string(argv[i]) == "-p") {
            print = true;
        }
        else if (std::string(argv[i]) == "-f") {
            c = parser.Read(std::string(argv[++i]));
            d = parser.Read(std::string(argv[++i]));
        }
        else {
            return BadUseError();
        }
    }

    start = clock();
    product1 = StrassenMult(64, c, d);
    stop = clock();
    std::cout << double(stop - start) / CLOCKS_PER_SEC << std::endl;

    c = parser.Read("/usagers/gwhud/Projects/AlgoLab1/tp1-donnees/ex_n5.1");
    d = parser.Read("/usagers/gwhud/Projects/AlgoLab1/tp1-donnees/ex_n5.2");
    start = clock();
    product2 = StrassenMult(64, c, d);
    stop = clock();

    bool equality = product1 == product2;
    std::cout << equality << std::endl;

    return 0;
}

