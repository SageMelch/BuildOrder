import java.util.*;

public class BuildOrder {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.println("Type done to finish");
        System.out.println("Enter a list of projects (One project at a time)");
        List<String> projects = new ArrayList<>();
        List<String> dependencies = new ArrayList<>();
        while (input.hasNext()) {
            String project = input.nextLine();
            if (project.equals("done") || project.equals("Done")) {
                break;
            } else {
                projects.add(project);
            }
        }
        System.out.println("Enter project dependencies:");
        System.out.println("Format: (projectA, projectB)");
        while (input.hasNext()) {
            String dependency = input.nextLine();
            if (dependency.equals("done") || dependency.equals("Done")) {
                System.out.println(buildOrder(projects, dependencies));
                break;
            } else {
                dependencies.add(dependency);
            }
        }
    }
    public static List<String> buildOrder(List<String> projects, List<String> dependencies) {
        try {
            List<String> order = new ArrayList<>();
            int count = 0;
            for (String project : projects) {
                for (int i = 0; i < dependencies.size(); i++) {
                    int parenthesesIndex = dependencies.get(i).indexOf(")");
                    int space = dependencies.get(i).indexOf(" ");
                    if (!project.equals(dependencies.get(i).substring(space+1, parenthesesIndex))) {
                        count++;
                    }
                }
                if (count == dependencies.size()) {
                    order.add(project);
                }
                count = 0;
            }
            for(int i=0; i < order.size(); i++) {
                for (int j = 0; j < dependencies.size(); j++) {
                    int index = dependencies.get(j).indexOf(",");
                    int parenthesesIndex = dependencies.get(j).indexOf(")");
                    if (order.get(i).equals(dependencies.get(j).substring(1, index).trim()) && !order.contains(dependencies.get(j).substring(index+1, parenthesesIndex).trim())) {
                        order.add(dependencies.get(j).substring(index+1, parenthesesIndex).trim());
                    }
                }
            }
            if(order.size() == projects.size()){
                return order;
            }
        } catch (StringIndexOutOfBoundsException ex) {
            System.out.println("Not a valid input!");
            ex.printStackTrace();
        }
        return Collections.singletonList("error");
    }
}