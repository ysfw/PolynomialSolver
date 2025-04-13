import java.util.Scanner;

public class PolynomialSolver implements IPolynomialSolver {
    DLL[] polynomials = new DLL[4];

    public PolynomialSolver() {
        for (int i = 0; i < 4; i++) {
            polynomials[i] = new DLL();
        }
    }

    public void setPolynomial(char poly, int[][] terms) {
        DLL p = polynomials[poly - 'A'];
        p.clear();
        for (int i = 0; i < terms.length; i++) {
            p.add(terms[i][1]);
        }
    }

    public String print(char poly) {
        String result = "";
        DLL p = polynomials[poly - 'A'];
        if (p.size() == 0) {
            throw new Error("Invalid Operation with clear polynomial");
        }
        for (int i = 0; i < p.size(); i++) {
            int coefficient = (int) p.get(i);
            int exp = p.size() - i - 1;
            if (coefficient == 0 && p.size() > 1) {
                if (result.endsWith("+")) {
                    result = result.substring(0, result.length() - 1);
                }
                continue;
            }
            if (i > 0) {
                String sign = (coefficient > 0) ? "+" : "";
                result += sign;
            }
            String co = (coefficient < 0) ? "-" : "";
            if ((Math.abs(coefficient) != 1 && exp > 0) || exp == 0) {
                co += Math.abs(coefficient);
            }
            String xPower = "";
            if (exp == 1) {
                xPower += "x";
            }
            else if (exp > 1) {
                xPower += "x^" + exp;
            }
            result = result + co + xPower;
        }
        return result;
    }

    public void clearPolynomial(char poly) {
        DLL p = polynomials[poly - 'A'];
        if (p.size() == 0) {
            throw new Error("Invalid Operation with clear polynomial");
        }
        p.clear();
        p.printList();
    }

    public float evaluatePolynomial(char poly, float value) {
        DLL p = polynomials[poly - 'A'];
        if (p.size() == 0) {
            throw new Error("Invalid Operation with clear polynomial");
        }
        float result = 0;
        for (int i = 0; i < p.size(); i++) {
            int coefficient = (int) p.get(i);
            result += (float) (coefficient * Math.pow(value, (p.size() - i - 1)));
        }
        return result;
    }

    public int[][] add(char poly1, char poly2) {
        int[][] result = null;
        DLL p1 = polynomials[poly1 - 'A'];
        DLL p2 = polynomials[poly2 - 'A'];
        if (p1.size() == 0 || p2.size() == 0) {
            throw new Error("Invalid Operation with clear polynomial");
        }
        int index = Math.abs(p1.size() - p2.size());
        if (p1.size() > p2.size()) {
            int exp = p1.size() - 1;
            result = new int[p1.size()][2];
            DLLNode curr1 = p1.getHead();
            for (int i = 0; i < index; i++) {
                result[i][0] = exp;
                result[i][1] = (int) curr1.getElement();
                curr1 = curr1.getNext();
                exp--;
            }
            DLLNode curr2 = p2.getHead();
            for (int i = index; i < p1.size(); i++) {
                result[i][0] = exp;
                result[i][1] = (int) curr1.getElement() + (int) curr2.getElement();
                curr1 = curr1.getNext();
                curr2 = curr2.getNext();
                exp--;
            }
        } else {
            int exp = p2.size() - 1;
            result = new int[p2.size()][2];
            DLLNode curr1 = p2.getHead();
            for (int i = 0; i < index; i++) {
                result[i][0] = exp;
                result[i][1] = (int) curr1.getElement();
                curr1 = curr1.getNext();
                exp--;
            }
            DLLNode curr2 = p1.getHead();
            for (int i = index; i < p2.size(); i++) {
                result[i][0] = exp;
                result[i][1] = (int) curr1.getElement() + (int) curr2.getElement();
                curr1 = curr1.getNext();
                curr2 = curr2.getNext();
                exp--;
            }
        }
        setPolynomial('D', result);
        while (((int)polynomials[3].getHead().getElement()) == 0 && polynomials[3].size() > 1) {
            polynomials[3].remove(0);
        }
        return result;
    }

    public int[][] subtract(char poly1, char poly2) {
        DLL negaDll = polynomials[poly2 - 'A'].sublist(0, polynomials[poly2 - 'A'].size() - 1);
        DLLNode curr = negaDll.getHead();
        for (int i = 0; i < negaDll.size(); i++) {
            curr.setElement(((int)curr.getElement()) * -1);
            curr = curr.getNext();
        }
        int[][] result = null;
        DLL p1 = polynomials[poly1 - 'A'];
        DLL p2 = negaDll;
        if (p1.size() == 0 || p2.size() == 0) {
            throw new Error("Invalid Operation with clear polynomial");
        }
        int index = Math.abs(p1.size() - p2.size());
        if (p1.size() > p2.size()) {
            int exp = p1.size() - 1;
            result = new int[p1.size()][2];
            DLLNode curr1 = p1.getHead();
            for (int i = 0; i < index; i++) {
                result[i][0] = exp;
                result[i][1] = (int) curr1.getElement();
                curr1 = curr1.getNext();
                exp--;
            }
            DLLNode curr2 = p2.getHead();
            for (int i = index; i < p1.size(); i++) {
                result[i][0] = exp;
                result[i][1] = (int) curr1.getElement() + (int) curr2.getElement();
                curr1 = curr1.getNext();
                curr2 = curr2.getNext();
                exp--;
            }
        } else {
            int exp = p2.size() - 1;
            result = new int[p2.size()][2];
            DLLNode curr1 = p2.getHead();
            for (int i = 0; i < index; i++) {
                result[i][0] = exp;
                result[i][1] = (int) curr1.getElement();
                curr1 = curr1.getNext();
                exp--;
            }
            DLLNode curr2 = p1.getHead();
            for (int i = index; i < p2.size(); i++) {
                result[i][0] = exp;
                result[i][1] = (int) curr1.getElement() + (int) curr2.getElement();
                curr1 = curr1.getNext();
                curr2 = curr2.getNext();
                exp--;
            }

        }
        setPolynomial('D', result);
        while (((int)polynomials[3].getHead().getElement()) == 0 && polynomials[3].size() > 1) {
            polynomials[3].remove(0);
        }
        return result;

    }

    public int[][] multiply(char poly1, char poly2) {
        DLL p1 = polynomials[poly1 - 'A'];
        DLL p2 = polynomials[poly2 - 'A'];
        if (p1.size() == 0 || p2.size() == 0) {
            throw new Error("Invalid Operation with clear polynomial");
        }
        int diff = Math.abs(p1.size() - p2.size());
        if (p1.size() < p2.size()) {
            for (int i = 0; i < diff; i++) {
                p1.add(0, 0);
            }
        } else {
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
            sum += (int) i.getElement() * (int) j.getElement();
            if (i != p1.getHead()) {
                i = i.getPrev();
                j = j.getNext();
            } else {
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
            sum += (int) i.getElement() * (int) j.getElement();
            if (j != p2.getTail()) {
                i = i.getPrev();
                j = j.getNext();
            } else {
                result.add(sum);
                sum = 0;
                j = lastRef.getNext();
                lastRef = lastRef.getNext();
                i = p1.getTail();
            }
        }
        while ((int) (result.getHead().getElement()) == 0 && result.size() > 1) {
            result.remove(0);
        }

        int length = result.size();
        int[][] final_result = new int[length][2];
        DLLNode curr = result.getHead();
        for (int k = 0; k < length; k++) {
            final_result[k][0] = length - k - 1;
            final_result[k][1] = (int) curr.getElement();
            curr = curr.getNext();
        }
        setPolynomial('D', final_result);
        return final_result;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        try {
            PolynomialSolver solver = new PolynomialSolver();
            while (sc.hasNextLine()) {
                String operation = sc.nextLine();
                if (operation.equals("set")) {
                    String characterString = sc.nextLine();
                    char poly = characterString.charAt(0);
                    String input = sc.nextLine();
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
                else if (operation.equals("print")) {
                    String characterString = sc.nextLine();
                    char poly = characterString.charAt(0);
                    System.out.println(solver.print(poly));
                }
                else if (operation.equals("add")) {
                    String characterString = sc.nextLine();
                    char poly1 = characterString.charAt(0);
                    characterString = sc.nextLine();
                    char poly2 = characterString.charAt(0);
                    solver.add(poly1, poly2);
                    System.out.println(solver.print('D'));
                }
                else if (operation.equals("sub")) {
                    String characterString = sc.nextLine();
                    char poly1 = characterString.charAt(0);
                    characterString = sc.nextLine();
                    char poly2 = characterString.charAt(0);
                    solver.subtract(poly1, poly2);
                    System.out.println(solver.print('D'));
                }
                else if (operation.equals("mult")) {
                    String characterString = sc.nextLine();
                    char poly1 = characterString.charAt(0);
                    characterString = sc.nextLine();
                    char poly2 = characterString.charAt(0);
                    solver.multiply(poly1, poly2);
                    System.out.println(solver.print('D'));
                }
                else if (operation.equals("clear")) {
                    String characterString = sc.nextLine();
                    char poly = characterString.charAt(0);
                    solver.clearPolynomial(poly);
                }
                else if (operation.equals("eval")) {
                    String characterString = sc.nextLine();
                    char poly = characterString.charAt(0);
                    float a = Float.parseFloat(sc.nextLine());
                    float result = solver.evaluatePolynomial(poly, a);
                    System.out.println(Math.round(result));
                }
                else {
                    throw new Exception("Invalid Operation");
                }
            }
        }
        catch (Exception | Error e) {
            sc.close();
            System.out.println("Error");
        }
        finally {
            sc.close();
        }
    }
}
