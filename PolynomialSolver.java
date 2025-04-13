import java.util.Scanner;

public class PolynomialSolver implements IPolynomialSolver {
    DLL[] polynomials = new DLL[4];
    public PolynomialSolver() {
        for (int i = 0; i < 4; i++) {
            polynomials[i] = new DLL();
        }
    }

    public void setPolynomial(char poly, int[][] terms) {
        for (int i = 0; i < terms.length; i++) {
            polynomials[poly - 'A'].add(terms[i][1]);
        }

    }

    public String print(char poly) {
        String result = "";
        DLL p = polynomials[poly - 'A'];
        for (int i = 0; i < p.size(); i++) {
            int coefficient = (int) p.get(i);
            result = result + coefficient + "X^" + (p.size() - i);
            if (i < p.size() - 1) {
                result += (coefficient < 0) ? "-" : "+";
            }
        }
        return result;
    }

    public void clearPolynomial(char poly) {
        polynomials[poly - 'A'].clear();
    }

    public float evaluatePolynomial(char poly, float value) {
        DLL p = polynomials[poly - 'A'];
        float result = 0;
        for (int i = 0; i < p.size(); i++) {
            int coefficient = (int) p.get(i);
            result += (float) (coefficient * Math.pow(value, (p.size() - i)));
        }
        return result;
    }

    public int[][] add(char poly1, char poly2) {
        int[][] result = null;
        DLL p1 = polynomials[poly1 - 'A'];
        DLL p2 = polynomials[poly2 - 'A'];
        int index = Math.abs(p1.size() - p2.size());
        if (p1.size()> p2.size()) {
            int exp = p1.size() -1 ;
            result = new int[p1.size()][2];
            DLLNode curr1 = p1.getHead(); 
            for (int i = 0; i < index; i++) {
                result[i][0]=exp;
                result[i][1]=(int)curr1.getElement();
                curr1 = curr1.getNext();
                exp--;
            }
            DLLNode curr2 = p2.getHead();
            for (int i = 0; i < p2.size(); i++) {
                result[i][0]=exp;
                result[i][1]=(int)curr1.getElement() + (int)curr2.getElement();
                curr1 = curr1.getNext();
                curr2 = curr2.getNext();
                exp--;
            }
        }
        else{
            int exp = p2.size() -1 ;
            result = new int[p2.size()][2];
            DLLNode curr1 = p2.getHead(); 
            for (int i = 0; i < index; i++) {
                result[i][0]=exp;
                result[i][1]=(int)curr1.getElement();
                curr1 = curr1.getNext();
                exp--;
            }
            DLLNode curr2 = p1.getHead();
            for (int i = 0; i < p1.size(); i++) {
                result[i][0]=exp;
                result[i][1]=(int)curr1.getElement() + (int)curr2.getElement();
                curr1 = curr1.getNext();
                curr2 = curr2.getNext();
                exp--;
            }

        }
        setPolynomial('D', result);
        return result;
    }

    public int[][] subtract(char poly1, char poly2) {
        DLL negaDll = polynomials[poly2 - 'A'];
        DLLNode curr = negaDll.getHead();
        for (int i = 0; i < negaDll.size(); i++) {
            curr.setElement((int)curr.getElement() * -1);
        }
        int[][] result = null;
        DLL p1 = polynomials[poly1 - 'A'];
        DLL p2 = negaDll;
        int index = Math.abs(p1.size() - p2.size());
        if (p1.size()> p2.size()) {
            int exp = p1.size() -1 ;
            result = new int[p1.size()][2];
            DLLNode curr1 = p1.getHead(); 
            for (int i = 0; i < index; i++) {
                result[i][0]=exp;
                result[i][1]=(int)curr1.getElement();
                curr1 = curr1.getNext();
                exp--;
            }
            DLLNode curr2 = p2.getHead();
            for (int i = 0; i < p2.size(); i++) {
                result[i][0]=exp;
                result[i][1]=(int)curr1.getElement() + (int)curr2.getElement();
                curr1 = curr1.getNext();
                curr2 = curr2.getNext();
                exp--;
            }
        }
        else{
            int exp = p2.size() -1 ;
            result = new int[p2.size()][2];
            DLLNode curr1 = p2.getHead(); 
            for (int i = 0; i < index; i++) {
                result[i][0]=exp;
                result[i][1]=(int)curr1.getElement();
                curr1 = curr1.getNext();
                exp--;
            }
            DLLNode curr2 = p1.getHead();
            for (int i = 0; i < p1.size(); i++) {
                result[i][0]=exp;
                result[i][1]=(int)curr1.getElement() + (int)curr2.getElement();
                curr1 = curr1.getNext();
                curr2 = curr2.getNext();
                exp--;
            }

        }
        setPolynomial('D', result);
        return result;
        

    }

    public int[][] multiply(char poly1, char poly2) {
        DLL p1 = polynomials[poly1 - 'A'];
        DLL p2 = polynomials[poly2 - 'A'];
        int diff = Math.abs(p1.size() - p2.size());
        if (p1.size() < p2.size()) {
            for (int i = 0; i < diff; i++) {
                p1.add(0, 0);
            }
        }
        else {
            for (int i = 0; i < diff; i++) {
                p2.add(0, 0);
            }
        }
        
        DLL result = polynomials[3]; // the variable R to store result
        result.clear();

        DLLNode i = p1.getHead();
        DLLNode j = p2.getHead();
        // i and j are nodes
        int sum = 0;
        DLLNode lastRef = i;
        while (i != null) {
            sum += (int)i.getElement() * (int)j.getElement();
            if (i != p1.getHead()) {
                i = i.getPrev();
                j = j.getNext();
            }
            else {
                result.add(sum);
                sum = 0;
                i = lastRef.getNext();
                lastRef = lastRef.getNext();
                j = p2.getHead();
            }
        }
        i = p1.getTail();
        j = p2.getHead().getNext();
        lastRef = j;
        while (j != null) {
            sum += (int)i.getElement() + (int)j.getElement();
            if (j != p2.getTail()) {
                i = i.getPrev();
                j = j.getNext();
            }
            else {
                result.add(sum);
                sum = 0;
                j = lastRef.getNext();
                lastRef = lastRef.getNext();
                i = p2.getTail();
            }
        }
        result.printList(); // debugging
        while ((int)(result.getHead().getElement()) == 0)
        {
            result.remove(0);
        }
        result.printList(); // debugging

        int length = result.size();
        int[][] final_result = new int[length][2];
        DLLNode curr = result.getHead();
        for (int k = 0; k < length; k++) {
            final_result[k][0] = length - k - 1;
            final_result[k][1] = (int)curr.getElement();
            curr = curr.getNext();
        }
        return final_result;
    }

    public static void main(String[] args) {
        PolynomialSolver solver = new PolynomialSolver();
        Scanner scan = new Scanner(System.in);
        String input = scan.nextLine();
        if (input.equals("set")) {
            String characteString = scan.nextLine();
            char poly = characteString.charAt(0);
            input = scan.nextLine();
            input = input.replaceAll("\\[|\\]", "");
            String[] inputList = input.split(", *");
            int length = inputList.length;
            int[][] terms = new int[length][2];
            for (int i = 0; i < length; i++) {
                terms[i][0] = length - 1 - i;
                terms[i][1] = Integer.parseInt(inputList[i]);
            }
            solver.setPolynomial(poly, terms);
        }
        else if (input.equals("print")) {
            
        }
        else if (input.equals("add")) {
            
        }
        else if (input.equals("sub")) {
            
        }
        else if (input.equals("mult")) {
            
        }
        else if (input.equals("clear")) {
            
        }
        else if (input.equals("eval")) {
            
        }
        
        scan.close();
    }
}
