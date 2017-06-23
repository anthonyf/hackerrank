import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by antho on 4/10/2017.
 */
public class CoinChange {
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
        String str = lines.get(0);
        solve(Arrays.stream(str.split(", ")).map((s) -> Integer.parseInt(s)));
    }

    static void solve(Stream<Integer> ints) {
        int i = ints.reduce(0, (a, b) -> { return a ^= b ;});
        System.out.println(i);
    }
}
