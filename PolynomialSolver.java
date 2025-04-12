
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
        // TODO Auto-generated method stub
        return null;

    }
}
