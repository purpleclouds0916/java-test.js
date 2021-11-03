package thinningoptim;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.stream.Collectors;

public class SA2021 {
    static {
        String classpath = System.getProperty("java.class.path");
        System.out.println("Importing cuOptimThinning.dll expected in " + classpath);
        String[] paths = Arrays.stream(classpath.split(";")).map(s -> new File(s).getAbsoluteFile().getParentFile().getAbsolutePath()).toArray(String[]::new);
        boolean success = false;
        for (String path : paths) {
            try {
                System.load(path + "//cuOptimThinning.dll");
                success = true;
            }catch (UnsatisfiedLinkError e){
                //None
            }
        }
        if(success == false)throw new UnsatisfiedLinkError("cuOptimThinning.dll was not found. The dll should be placed at either of \n"+ Arrays.stream(paths).collect(Collectors.joining("\n")));
        System.out.println("cuOptimThinning.dll was imported successfully.");
    }

    public static native String run(String _json);

    public static void main(String[] _args) throws IOException {
        System.out.println("""
                This is a demonstration to use "run(String _jsonString)".
                The first argument should be the path of a json input file.
                The file is imported as String (_jsonString).
                Then "run(_jsonString)" is executed and the output is generated.
                For more detail, see the source code of this Java code""");
        Path givenFileName = Paths.get(_args[0]);
        String jsonString = Files.readString(givenFileName);
        String res = run(jsonString);
        System.out.println(res);
    }

}
