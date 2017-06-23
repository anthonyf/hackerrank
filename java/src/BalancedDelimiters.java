import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Stack;
import java.util.stream.Collectors;

public class BalancedDelimiters {
    public static void main (String[] args) throws IOException {
        if(args.length == 1) {
            solve(Files.newInputStream(Paths.get(args[0])));
        } else {
            solve(System.in);
        }
    }

    static void solve(InputStream in) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(in));
        Stack<Integer> s = new Stack<Integer>();
        while(true) {
            int c = br.read();
            if (c == -1) {
                System.out.println(s.size() == 0 ? "True" : "False");
                return;
            }
            switch(c) {
                case '{':
                case '(':
                case '[':
                    s.push(c);
                    break;
                case '}':
                case ')':
                case ']':
                    int o = s.pop();
                    if(opposite(c) != o)
                    {
                        System.out.println("False");
                        return;
                    }
                    break;
                default:

            }
        }
    }

    private static int opposite(int c) {
        switch(c) {
            case '}':
                return '{';
            case ')':
                return '(';
            case ']':
                return '[';
        }
        throw new UnsupportedOperationException("Invalid brace " + Character.toString((char)c));
    }

}
