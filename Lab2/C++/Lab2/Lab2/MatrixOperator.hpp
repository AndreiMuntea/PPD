#ifndef MATRIX_OPERATOR_HPP
#define MATRIX_OPERATOR_HPP

#include "Matrix.hpp"

template <class T>
class MatrixOperator
{
public:
	MatrixOperator() = delete;

	static void Add(
	    _In_ Matrix<T>& FirstMatrix,
		_In_ Matrix<T>& SecondMatrix,
		_Inout_ Matrix<T>& Result,
		_In_ size_t LineStart,
		_In_ size_t LineEnd
	)
	{
		for (size_t i = LineStart; i < LineEnd; ++i)
		{
			for (size_t j = 0; j < FirstMatrix.GetNoColumns(); ++j)
			{
				Result[i][j] = FirstMatrix[i][j] + SecondMatrix[i][j];
			}
		}
	}

	static void Multiply(
		_In_ Matrix<T>& FirstMatrix,
		_In_ Matrix<T>& SecondMatrix,
		_Inout_ Matrix<T>& Result,
		_In_ size_t LineStart,
		_In_ size_t LineEnd
	)
	{
		for (auto i = LineStart; i < LineEnd; ++i)
		{
			for (auto j = 0; j < SecondMatrix.GetNoColumns(); ++j)
			{
				Result[i][j] = 0;
				for (auto k = 0; k < SecondMatrix.GetNoLines(); ++k)
				{
					Result[i][j] += FirstMatrix[i][k] * SecondMatrix[k][j];
				}
			}
		}
	}

	static void CustomOperator1(
		_In_ Matrix<T>& FirstMatrix,
		_In_ Matrix<T>& SecondMatrix,
		_Inout_ Matrix<T>& Result,
		_In_ size_t LineStart,
		_In_ size_t LineEnd
	)
	{
		T one{ 1 };
		T zero{ 0 };
		for (size_t i = LineStart; i < LineEnd; ++i)
		{
			for (size_t j = 0; j < FirstMatrix.GetNoColumns(); ++j)
			{
				if (FirstMatrix[i][j] == zero && SecondMatrix[i][j] == zero)
				{
					Result[i][j] = zero;
				}
				else if (FirstMatrix[i][j] == zero)
				{
					T temp = one / SecondMatrix[i][j];	
					Result[i][j] = (temp == zero) ? zero : (one / temp);
				}
				else if (SecondMatrix[i][j] == zero)
				{
					T temp = one / FirstMatrix[i][j];
					Result[i][j] = (temp == zero) ? zero : (one / temp);
				}
				else
				{
					T temp = one / FirstMatrix[i][j] + one / SecondMatrix[i][j];
					Result[i][j] = (temp == zero) ? zero : (one / temp);
				}
			}
		}
	}

};


#endif // MATRIX_OPERATOR_HPP