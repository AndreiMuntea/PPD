#ifndef RANDOMIZER_HPP
#define RANDOMIZER_HPP

#include "Matrix.hpp"

#include <random>
#include <complex>
#include <algorithm>

class Randomizer
{
private:

	static const double LOWER_BOUND;
    static const double UPPER_BOUND;

    static std::uniform_real_distribution<double> UNIFORM;
	static std::default_random_engine RANDOM_ENGINE;

public:

	Randomizer() = delete;

	static Matrix<double> GenerateDoubleMatrix(
		_In_ size_t NumberOfLines,
		_In_ size_t NumberOfColumns
	)
	{
		std::vector<std::vector<double>> v(NumberOfLines, std::vector<double>(NumberOfColumns));
		for (size_t i = 0; i < NumberOfLines; ++i)
		{
			for (size_t j = 0; j < NumberOfColumns; ++j)
			{
				v[i][j] = UNIFORM(RANDOM_ENGINE);
			}
		}

		return Matrix<double>{v};
	}

	static Matrix<std::complex<double>> GenerateComplexMatrix(
		_In_ size_t NumberOfLines,
		_In_ size_t NumberOfColumns
	)
	{
		std::vector<std::vector<std::complex<double>>> v(NumberOfLines, std::vector<std::complex<double>>(NumberOfColumns));
		for (size_t i = 0; i < NumberOfLines; ++i)
		{
			for (size_t j = 0; j < NumberOfColumns; ++j)
			{
				v[i][j] = std::complex<double>{ UNIFORM(RANDOM_ENGINE), UNIFORM(RANDOM_ENGINE) };
			}
		}

		return Matrix<std::complex<double>>{v};
	}
};

const double Randomizer::LOWER_BOUND{ -100 };
const double Randomizer::UPPER_BOUND{ 100 };

std::uniform_real_distribution<double> Randomizer::UNIFORM{ Randomizer::LOWER_BOUND, Randomizer::UPPER_BOUND };
std::default_random_engine Randomizer::RANDOM_ENGINE{};


#endif //RANDOMIZER_HPP