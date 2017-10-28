#include "CppUnitTest.h"

#include "Matrix.hpp"
#include "Workers.hpp"
#include "Randomizer.hpp"

using namespace Microsoft::VisualStudio::CppUnitTestFramework;

namespace UnitTest1
{		
	TEST_CLASS(UnitTest1)
	{
	public:
		
		TEST_METHOD(TestAddDoubleSmallDataSet)
		{

			Logger::WriteMessage("TestAddDoubleSmallDataSet");

			Matrix<double> a{ {
				{ 1,2,3 },
				{ 7,8,9 },
				{ 4,5,6 }
				} };
			
			Matrix<double> b{ {
				{ 1,2,3 },
				{ 7,8,9 },
				{ 4,5,6 }
				} };

			Matrix<double> r0{ {
				{ 2,4,6 },
				{ 14,16,18 },
				{ 8,10,12 }
				} };
		

			Matrix<double> r1{ r0.GetNoLines(), r0.GetNoColumns() };

			Matrix<double> r2{ r0.GetNoLines(), r0.GetNoColumns() };

			Matrix<double> r4{ r0.GetNoLines(), r0.GetNoColumns() };

			Matrix<double> r8{ r0.GetNoLines(), r0.GetNoColumns() };

			Matrix<double> r64{ r0.GetNoLines(), r0.GetNoColumns() };

			double time1 = DoWork<double>(a, b, r1, 1, MatrixOperator<double>::Add);
			Logger::WriteMessage(("1 thread(s): " + std::to_string(time1)).c_str());

			double time2 = DoWork<double>(a, b, r2, 2, MatrixOperator<double>::Add);
			Logger::WriteMessage(("2 thread(s): " + std::to_string(time2)).c_str());

			double time4 = DoWork<double>(a, b, r4, 4, MatrixOperator<double>::Add);
			Logger::WriteMessage(("4 thread(s): " + std::to_string(time4)).c_str());

			double time8 = DoWork<double>(a, b, r8, 8, MatrixOperator<double>::Add);
			Logger::WriteMessage(("8 thread(s): " + std::to_string(time8)).c_str());

			double time64 = DoWork<double>(a, b, r64, 64, MatrixOperator<double>::Add);
			Logger::WriteMessage(("64 thread(s): " + std::to_string(time64)).c_str());
			
			Assert::IsTrue(r0 == r1);
			Assert::IsTrue(r1 == r2);
			Assert::IsTrue(r1 == r4);
			Assert::IsTrue(r1 == r8);
			Assert::IsTrue(r1 == r64);
		}

		TEST_METHOD(TestAddComplexSmallDataSet)
		{

			Logger::WriteMessage("TestAddComplexSmallDataSet");

			Matrix<std::complex<double>> a{ {
				{ std::complex<double>(3,4), std::complex<double>(3,4), std::complex<double>(3,4) },
				{ std::complex<double>(1,2), std::complex<double>(7,8), std::complex<double>(7,8) },
				{ std::complex<double>(5,6), std::complex<double>(3,4), std::complex<double>(3,4) },
				} };

			Matrix<std::complex<double>> b{ {
				{ std::complex<double>(3,4), std::complex<double>(3,4), std::complex<double>(3,4) },
				{ std::complex<double>(1,2), std::complex<double>(7,8), std::complex<double>(7,8) },
				{ std::complex<double>(5,6), std::complex<double>(3,4), std::complex<double>(3,4) },
				} };

			Matrix<std::complex<double>> r0{ {
				{ std::complex<double>(6,8), std::complex<double>(6,8), std::complex<double>(6,8) },
				{ std::complex<double>(2,4), std::complex<double>(14,16), std::complex<double>(14,16) },
				{ std::complex<double>(10,12), std::complex<double>(6,8), std::complex<double>(6,8) },
				} };

			Matrix<std::complex<double>> r1{ a.GetNoLines(), a.GetNoColumns() };

			Matrix<std::complex<double>> r2{ a.GetNoLines(), a.GetNoColumns() };

			Matrix<std::complex<double>> r4{ a.GetNoLines(), a.GetNoColumns() };

			Matrix<std::complex<double>> r8{ a.GetNoLines(), a.GetNoColumns() };

			Matrix<std::complex<double>> r64{ a.GetNoLines(), a.GetNoColumns() };

			double time1 = DoWork<std::complex<double>>(a, b, r1, 1, MatrixOperator<std::complex<double>>::Add);
			Logger::WriteMessage(("1 thread(s): " + std::to_string(time1)).c_str());

			double time2 = DoWork<std::complex<double>>(a, b, r2, 2, MatrixOperator<std::complex<double>>::Add);
			Logger::WriteMessage(("2 thread(s): " + std::to_string(time2)).c_str());

			double time4 = DoWork<std::complex<double>>(a, b, r4, 4, MatrixOperator<std::complex<double>>::Add);
			Logger::WriteMessage(("4 thread(s): " + std::to_string(time4)).c_str());

			double time8 = DoWork<std::complex<double>>(a, b, r8, 8, MatrixOperator<std::complex<double>>::Add);
			Logger::WriteMessage(("8 thread(s): " + std::to_string(time8)).c_str());

			double time64 = DoWork<std::complex<double>>(a, b, r64, 64, MatrixOperator<std::complex<double>>::Add);
			Logger::WriteMessage(("64 thread(s): " + std::to_string(time64)).c_str());

			Assert::IsTrue(r0 == r1);
			Assert::IsTrue(r1 == r2);
			Assert::IsTrue(r1 == r4);
			Assert::IsTrue(r1 == r8);
			Assert::IsTrue(r1 == r64);
		}

		TEST_METHOD(TestMultiplicationComplexSmallDataSet)
		{

			Logger::WriteMessage("TestMultiplicationComplexSmallDataSet");

			Matrix<std::complex<double>> a{ {
				{ std::complex<double>(3,4), std::complex<double>(3,4), std::complex<double>(3,4) },
				{ std::complex<double>(1,2), std::complex<double>(7,8), std::complex<double>(7,8) },
				{ std::complex<double>(5,6), std::complex<double>(3,4), std::complex<double>(3,4) },
				} };

			Matrix<std::complex<double>> b{ {
				{ std::complex<double>(3,4), std::complex<double>(3,4), std::complex<double>(3,4) },
				{ std::complex<double>(1,2), std::complex<double>(7,8), std::complex<double>(7,8) },
				{ std::complex<double>(5,6), std::complex<double>(3,4), std::complex<double>(3,4) },
				} };

			Matrix<std::complex<double>> r0{ {
				{ std::complex<double>(-21,72), std::complex<double>(-25,100), std::complex<double>(-25,100) },
				{ std::complex<double>(-27,114), std::complex<double>(-31,174), std::complex<double>(-31,174) },
				{ std::complex<double>(-23,86), std::complex<double>(-27,114), std::complex<double>(-27,114) },
				} };

			Matrix<std::complex<double>> r1{ a.GetNoLines(), a.GetNoColumns() };

			Matrix<std::complex<double>> r2{ a.GetNoLines(), a.GetNoColumns() };

			Matrix<std::complex<double>> r4{ a.GetNoLines(), a.GetNoColumns() };

			Matrix<std::complex<double>> r8{ a.GetNoLines(), a.GetNoColumns() };

			Matrix<std::complex<double>> r64{ a.GetNoLines(), a.GetNoColumns() };

			double time1 = DoWork<std::complex<double>>(a, b, r1, 1, MatrixOperator<std::complex<double>>::Multiply);
			Logger::WriteMessage(("1 thread(s): " + std::to_string(time1)).c_str());

			double time2 = DoWork<std::complex<double>>(a, b, r2, 2, MatrixOperator<std::complex<double>>::Multiply);
			Logger::WriteMessage(("2 thread(s): " + std::to_string(time2)).c_str());

			double time4 = DoWork<std::complex<double>>(a, b, r4, 4, MatrixOperator<std::complex<double>>::Multiply);
			Logger::WriteMessage(("4 thread(s): " + std::to_string(time4)).c_str());

			double time8 = DoWork<std::complex<double>>(a, b, r8, 8, MatrixOperator<std::complex<double>>::Multiply);
			Logger::WriteMessage(("8 thread(s): " + std::to_string(time8)).c_str());

			double time64 = DoWork<std::complex<double>>(a, b, r64, 64, MatrixOperator<std::complex<double>>::Multiply);
			Logger::WriteMessage(("64 thread(s): " + std::to_string(time64)).c_str());

			Assert::IsTrue(r0 == r1);
			Assert::IsTrue(r1 == r2);
			Assert::IsTrue(r1 == r4);
			Assert::IsTrue(r1 == r8);
			Assert::IsTrue(r1 == r64);
		}

		TEST_METHOD(TestAddDoubleBigDataSet)
		{

			Logger::WriteMessage("TestAddDoubleBigDataSet");

			Matrix<double> a = Randomizer::GenerateDoubleMatrix(1000, 1900);

			Matrix<double> b = Randomizer::GenerateDoubleMatrix(1000, 1900);

			Matrix<double> r1{ a.GetNoLines(), a.GetNoColumns() };

			Matrix<double> r2{ a.GetNoLines(), a.GetNoColumns() };

			Matrix<double> r4{ a.GetNoLines(), a.GetNoColumns() };

			Matrix<double> r8{ a.GetNoLines(), a.GetNoColumns() };

			Matrix<double> r64{ a.GetNoLines(), a.GetNoColumns() };

			double time1 = DoWork<double>(a, b, r1, 1, MatrixOperator<double>::Add);
			Logger::WriteMessage(("1 thread(s): " + std::to_string(time1)).c_str());

			double time2 = DoWork<double>(a, b, r2, 2, MatrixOperator<double>::Add);
			Logger::WriteMessage(("2 thread(s): " + std::to_string(time2)).c_str());

			double time4 = DoWork<double>(a, b, r4, 4, MatrixOperator<double>::Add);
			Logger::WriteMessage(("4 thread(s): " + std::to_string(time4)).c_str());

			double time8 = DoWork<double>(a, b, r8, 8, MatrixOperator<double>::Add);
			Logger::WriteMessage(("8 thread(s): " + std::to_string(time8)).c_str());

			double time64 = DoWork<double>(a, b, r64, 64, MatrixOperator<double>::Add);
			Logger::WriteMessage(("64 thread(s): " + std::to_string(time64)).c_str());

			Assert::IsTrue(r1 == r2);
			Assert::IsTrue(r1 == r4);
			Assert::IsTrue(r1 == r8);
			Assert::IsTrue(r1 == r64);
		}

		TEST_METHOD(TestAddComplexBigDataSet)
		{

			Logger::WriteMessage("TestAddComplexBigDataSet");

			Matrix<std::complex<double>> a = Randomizer::GenerateComplexMatrix(1000, 1900);

			Matrix<std::complex<double>> b = Randomizer::GenerateComplexMatrix(1000, 1900);

			Matrix<std::complex<double>> r1{ a.GetNoLines(), a.GetNoColumns() };

			Matrix<std::complex<double>> r2{ a.GetNoLines(), a.GetNoColumns() };

			Matrix<std::complex<double>> r4{ a.GetNoLines(), a.GetNoColumns() };

			Matrix<std::complex<double>> r8{ a.GetNoLines(), a.GetNoColumns() };

			Matrix<std::complex<double>> r64{ a.GetNoLines(), a.GetNoColumns() };

			double time1 = DoWork<std::complex<double>>(a, b, r1, 1, MatrixOperator<std::complex<double>>::Add);
			Logger::WriteMessage(("1 thread(s): " + std::to_string(time1)).c_str());

			double time2 = DoWork<std::complex<double>>(a, b, r2, 2, MatrixOperator<std::complex<double>>::Add);
			Logger::WriteMessage(("2 thread(s): " + std::to_string(time2)).c_str());

			double time4 = DoWork<std::complex<double>>(a, b, r4, 4, MatrixOperator<std::complex<double>>::Add);
			Logger::WriteMessage(("4 thread(s): " + std::to_string(time4)).c_str());

			double time8 = DoWork<std::complex<double>>(a, b, r8, 8, MatrixOperator<std::complex<double>>::Add);
			Logger::WriteMessage(("8 thread(s): " + std::to_string(time8)).c_str());

			double time64 = DoWork<std::complex<double>>(a, b, r64, 64, MatrixOperator<std::complex<double>>::Add);
			Logger::WriteMessage(("64 thread(s): " + std::to_string(time64)).c_str());

			Assert::IsTrue(r1 == r2);
			Assert::IsTrue(r1 == r4);
			Assert::IsTrue(r1 == r8);
			Assert::IsTrue(r1 == r64);
		}

		TEST_METHOD(TestMultiplicationDoubleSmallDataSet)
		{

			Logger::WriteMessage("TestMultiplicationDoubleSmallDataSet");


			Matrix<double> a{ {
				{ 1,2,3 },
				{ 7,8,9 },
				{ 4,5,6 }
				} };

			Matrix<double> b{ {
				{ 1,2,3 },
				{ 7,8,9 },
				{ 4,5,6 }
				} };

			Matrix<double> r0{ {
				{ 27, 33, 39 },
				{ 99, 123, 147 },
				{ 63, 78, 93 }
				} };


			Matrix<double> r1{ a.GetNoLines(), b.GetNoColumns() };

			Matrix<double> r2{ a.GetNoLines(), b.GetNoColumns() };

			Matrix<double> r4{ a.GetNoLines(), b.GetNoColumns() };

			Matrix<double> r8{ a.GetNoLines(), b.GetNoColumns() };

			Matrix<double> r64{ a.GetNoLines(), b.GetNoColumns() };

			double time1 = DoWork<double>(a, b, r1, 1, MatrixOperator<double>::Multiply);
			Logger::WriteMessage(("1 thread(s): " + std::to_string(time1)).c_str());

			double time2 = DoWork<double>(a, b, r2, 2, MatrixOperator<double>::Multiply);
			Logger::WriteMessage(("2 thread(s): " + std::to_string(time2)).c_str());

			double time4 = DoWork<double>(a, b, r4, 4, MatrixOperator<double>::Multiply);
			Logger::WriteMessage(("4 thread(s): " + std::to_string(time4)).c_str());

			double time8 = DoWork<double>(a, b, r8, 8, MatrixOperator<double>::Multiply);
			Logger::WriteMessage(("8 thread(s): " + std::to_string(time8)).c_str());

			double time64 = DoWork<double>(a, b, r64, 64, MatrixOperator<double>::Multiply);
			Logger::WriteMessage(("64 thread(s): " + std::to_string(time64)).c_str());

			Assert::IsTrue(r0 == r1);
			Assert::IsTrue(r1 == r2);
			Assert::IsTrue(r1 == r4);
			Assert::IsTrue(r1 == r8);
			Assert::IsTrue(r1 == r64);
		}

		TEST_METHOD(TestMultiplicationComplexBigDataSet)
		{

			Logger::WriteMessage("TestMultiplicationComplexBigDataSet");

			Matrix<std::complex<double>> a = Randomizer::GenerateComplexMatrix(1000, 1900);

			Matrix<std::complex<double>> b = Randomizer::GenerateComplexMatrix(1900, 1000);

			Matrix<std::complex<double>> r1{ a.GetNoLines(), b.GetNoColumns() };

			Matrix<std::complex<double>> r2{ a.GetNoLines(), b.GetNoColumns() };

			Matrix<std::complex<double>> r4{ a.GetNoLines(), b.GetNoColumns() };

			Matrix<std::complex<double>> r8{ a.GetNoLines(), b.GetNoColumns() };

			Matrix<std::complex<double>> r64{ a.GetNoLines(), b.GetNoColumns() };

			double time1 = DoWork<std::complex<double>>(a, b, r1, 1, MatrixOperator<std::complex<double>>::Multiply);
			Logger::WriteMessage(("1 thread(s): " + std::to_string(time1)).c_str());

			double time2 = DoWork<std::complex<double>>(a, b, r2, 2, MatrixOperator<std::complex<double>>::Multiply);
			Logger::WriteMessage(("2 thread(s): " + std::to_string(time2)).c_str());

			double time4 = DoWork<std::complex<double>>(a, b, r4, 4, MatrixOperator<std::complex<double>>::Multiply);
			Logger::WriteMessage(("4 thread(s): " + std::to_string(time4)).c_str());

			double time8 = DoWork<std::complex<double>>(a, b, r8, 8, MatrixOperator<std::complex<double>>::Multiply);
			Logger::WriteMessage(("8 thread(s): " + std::to_string(time8)).c_str());

			double time64 = DoWork<std::complex<double>>(a, b, r64, 64, MatrixOperator<std::complex<double>>::Multiply);
			Logger::WriteMessage(("64 thread(s): " + std::to_string(time64)).c_str());

			Assert::IsTrue(r1 == r2);
			Assert::IsTrue(r1 == r4);
			Assert::IsTrue(r1 == r8);
			Assert::IsTrue(r1 == r64);
		}

		TEST_METHOD(TestMultiplicationDoubleBigDataSet)
		{

			Logger::WriteMessage("TestMultiplicationDoubleBigDataSet");

			Matrix<double> a = Randomizer::GenerateDoubleMatrix(1000, 1900);

			Matrix<double> b = Randomizer::GenerateDoubleMatrix(1900, 1000);

			Matrix<double> r1{ a.GetNoLines(), b.GetNoColumns() };

			Matrix<double> r2{ a.GetNoLines(), b.GetNoColumns() };

			Matrix<double> r4{ a.GetNoLines(), b.GetNoColumns() };

			Matrix<double> r8{ a.GetNoLines(), b.GetNoColumns() };

			Matrix<double> r64{ a.GetNoLines(), b.GetNoColumns() };

			double time1 = DoWork<double>(a, b, r1, 1, MatrixOperator<double>::Multiply);
			Logger::WriteMessage(("1 thread(s): " + std::to_string(time1)).c_str());

			double time2 = DoWork<double>(a, b, r2, 2, MatrixOperator<double>::Multiply);
			Logger::WriteMessage(("2 thread(s): " + std::to_string(time2)).c_str());

			double time4 = DoWork<double>(a, b, r4, 4, MatrixOperator<double>::Multiply);
			Logger::WriteMessage(("4 thread(s): " + std::to_string(time4)).c_str());

			double time8 = DoWork<double>(a, b, r8, 8, MatrixOperator<double>::Multiply);
			Logger::WriteMessage(("8 thread(s): " + std::to_string(time8)).c_str());

			double time64 = DoWork<double>(a, b, r64, 64, MatrixOperator<double>::Multiply);
			Logger::WriteMessage(("64 thread(s): " + std::to_string(time64)).c_str());

			Assert::IsTrue(r1 == r2);
			Assert::IsTrue(r1 == r4);
			Assert::IsTrue(r1 == r8);
			Assert::IsTrue(r1 == r64);
		}


		TEST_METHOD(TestCustomOperator1DoubleBigDataSet)
		{

			Logger::WriteMessage("TestCustomOperator1DoubleBigDataSet");

			Matrix<double> a = Randomizer::GenerateDoubleMatrix(1000, 1000);

			Matrix<double> b = Randomizer::GenerateDoubleMatrix(1000, 1000);

			Matrix<double> r1{ a.GetNoLines(), b.GetNoColumns() };

			Matrix<double> r2{ a.GetNoLines(), b.GetNoColumns() };

			Matrix<double> r4{ a.GetNoLines(), b.GetNoColumns() };

			Matrix<double> r8{ a.GetNoLines(), b.GetNoColumns() };

			Matrix<double> r64{ a.GetNoLines(), b.GetNoColumns() };

			double time1 = DoWork<double>(a, b, r1, 1, MatrixOperator<double>::CustomOperator1);
			Logger::WriteMessage(("1 thread(s): " + std::to_string(time1)).c_str());

			double time2 = DoWork<double>(a, b, r2, 2, MatrixOperator<double>::CustomOperator1);
			Logger::WriteMessage(("2 thread(s): " + std::to_string(time2)).c_str());

			double time4 = DoWork<double>(a, b, r4, 4, MatrixOperator<double>::CustomOperator1);
			Logger::WriteMessage(("4 thread(s): " + std::to_string(time4)).c_str());

			double time8 = DoWork<double>(a, b, r8, 8, MatrixOperator<double>::CustomOperator1);
			Logger::WriteMessage(("8 thread(s): " + std::to_string(time8)).c_str());

			double time64 = DoWork<double>(a, b, r64, 64, MatrixOperator<double>::CustomOperator1);
			Logger::WriteMessage(("64 thread(s): " + std::to_string(time64)).c_str());

			Assert::IsTrue(r1 == r2);
			Assert::IsTrue(r1 == r4);
			Assert::IsTrue(r1 == r8);
			Assert::IsTrue(r1 == r64);
		}

		TEST_METHOD(TestCustomOperator1ComplexBigDataSet)
		{

			Logger::WriteMessage("TestCustomOperator1ComplexBigDataSet");

			Matrix<std::complex<double>> a = Randomizer::GenerateComplexMatrix(1000, 1000);

			Matrix<std::complex<double>> b = Randomizer::GenerateComplexMatrix(1000, 1000);

			Matrix<std::complex<double>> r1{ a.GetNoLines(), b.GetNoColumns() };

			Matrix<std::complex<double>> r2{ a.GetNoLines(), b.GetNoColumns() };

			Matrix<std::complex<double>> r4{ a.GetNoLines(), b.GetNoColumns() };

			Matrix<std::complex<double>> r8{ a.GetNoLines(), b.GetNoColumns() };

			Matrix<std::complex<double>> r64{ a.GetNoLines(), b.GetNoColumns() };

			double time1 = DoWork<std::complex<double>>(a, b, r1, 1, MatrixOperator<std::complex<double>>::CustomOperator1);
			Logger::WriteMessage(("1 thread(s): " + std::to_string(time1)).c_str());

			double time2 = DoWork<std::complex<double>>(a, b, r2, 2, MatrixOperator<std::complex<double>>::CustomOperator1);
			Logger::WriteMessage(("2 thread(s): " + std::to_string(time2)).c_str());

			double time4 = DoWork<std::complex<double>>(a, b, r4, 4, MatrixOperator<std::complex<double>>::CustomOperator1);
			Logger::WriteMessage(("4 thread(s): " + std::to_string(time4)).c_str());

			double time8 = DoWork<std::complex<double>>(a, b, r8, 8, MatrixOperator<std::complex<double>>::CustomOperator1);
			Logger::WriteMessage(("8 thread(s): " + std::to_string(time8)).c_str());

			double time64 = DoWork<std::complex<double>>(a, b, r64, 64, MatrixOperator<std::complex<double>>::CustomOperator1);
			Logger::WriteMessage(("64 thread(s): " + std::to_string(time64)).c_str());

			Assert::IsTrue(r1 == r2);
			Assert::IsTrue(r1 == r4);
			Assert::IsTrue(r1 == r8);
			Assert::IsTrue(r1 == r64);
		}

	};
}