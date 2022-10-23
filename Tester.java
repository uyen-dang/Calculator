public class Tester {
	public static void main(String[] args){
		
        if (Postfixer.infixEvaluator("10 + 2") != 12)
            System.err.println("test1 failed --> answer should have been 12");

        if (Postfixer.infixEvaluator("10 - 2 * 2 + 1") != 7)
            System.err.println("test2 failed --> answer should have been 7");

        if (Postfixer.infixEvaluator("100 * 2 + 12") != 212)
            System.err.println("test3 failed --> should have been 212");

        if (Postfixer.infixEvaluator("100 * ( 2 + 12 )") != 1400)
            System.err.println("test4 failed --> should have been 1400");

        if (Postfixer.infixEvaluator("100 * ( 2 + 12 ) / 14") != 100)
            System.err.println("test5 failed --> should have been 100");

		if (!Postfixer.toPostfix(new String("(4+5)")).equals("45+"))
			System.err.println("test6 failed --> should have been 45+");

		if (!Postfixer.toPostfix(new String("((4+5)*6)")).equals("45+6*"))
		    System.err.println("test7 failed --> should have been 45+6*");

		if (!Postfixer.toPostfix(new String("((4+((5*6)/7))-8)")).equals("456*7/+8-"))
		    System.err.println("test8 failed --> should have been 456*7/+8-");

		if (!Postfixer.toPostfix(new String("((4+5*(6-7))/8)")).equals("4567-*+8/"))
		    System.err.println("test9 failed --> should have been 4567-*+8/");
		
		if (!Postfixer.toPostfix(new String("(9+(8*7-(6/5^4)*3)*2)")).equals("987*654^/3*-2*+"))
		    System.err.println("test10 failed --> should have been 987*654^/3*-2*+");


        System.out.println("All tests passed.");
	}
}
