import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class MthToLast {
    public static void main (String[] args) throws IOException {
        if(args.length == 1) {
            solve(Files.newInputStream(Paths.get(args[0])));
        } else {
            solve(System.in);
        }
    }

    static void solve(InputStream in) {
        BufferedReader br = new BufferedReader(new InputStreamReader(in));
        List<String> lines = br.lines().collect(Collectors.toList());
        int i = Integer.parseInt(lines.get(0));
        String str = lines.get(1);
        solve(i, str.split(" "));
    }

    static void solve(int m, String[] ints) {
        int i = ints.length-m;
        if(i < 0 || i >= ints.length) {
            System.out.println("NIL");
        } else {
            System.out.println(ints[i]);
        }
    }
}
