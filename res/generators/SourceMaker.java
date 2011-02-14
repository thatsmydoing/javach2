import java.io.*;
import java.util.*;

public class SourceMaker {
  public static void main(String[] args) throws Exception {
    String templateFile = args[0];
    String inputFile = args[1];
    String prefix = args[2];
    int count = (args.length > 3) ? Integer.parseInt(args[3]) : 1;

    List<TemplateLine> template = new ArrayList<TemplateLine>();
    Scanner templateScanner = new Scanner(new File(templateFile));
    while (templateScanner.hasNextLine()) {
      template.add(new TemplateLine(templateScanner.nextLine()));
    }

    Scanner inputScanner = new Scanner(new File(inputFile));
    while (inputScanner.hasNextLine()) {
      String className = String.format("%s_%02d", prefix, count);
      StringBuilder text = new StringBuilder();
      text.append("public class ");
      text.append(className);
      text.append(" {\n");
      String[] input = inputScanner.nextLine().split("\\s");
      for (TemplateLine t : template) {
        text.append(t.getOutput(input));
      }
      text.append("}");
      PrintStream writer = new PrintStream(new File(className + ".java"));
      writer.println(text.toString());
      System.out.printf("Wrote class %s\n", className);
      count++;
    }
  }
}

class TemplateLine {
  int colNo;
  String value;
  String output;

  TemplateLine(String input) {
    Scanner s = new Scanner(input);
    colNo = s.nextInt();
    value = s.next();
    output = s.nextLine();
    output = output.substring(1);
    output = output.replace("\\n", "\n");
  }

  public String getOutput(String[] input) {
    if (colNo < 0 || (colNo < input.length && input[colNo].equals(value))) {
      return output;
    }
    return "";
  }
}