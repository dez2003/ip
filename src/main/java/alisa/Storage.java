package alisa;

import alisa.task.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {
    private String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    public ArrayList<Task> loadFile() throws AlisaException {
        ArrayList<Task> taskList = new ArrayList<>();
        try {
            Scanner sc = new Scanner(new File(filePath));
            while (sc.hasNextLine()) {
                String line = sc.nextLine();
                String[] inputArray = line.split(" \\| ");
                String taskType = inputArray[0];
                String isDone = inputArray[1];

                switch (taskType) {
                    case "T":
                        Todo newTodo = new Todo(inputArray[2]);
                        if (isDone.equals("1")) {
                            newTodo.markAsDone();
                        }
                        taskList.add(newTodo);
                        break;
                    case "D":
                        Deadline newDeadline = new Deadline(inputArray[2], inputArray[3]);
                        if (isDone.equals("1")) {
                            newDeadline.markAsDone();
                        }
                        taskList.add(newDeadline);
                        break;
                    case "E":
                        String fromTo = inputArray[3];
                        String[] fromToArray = fromTo.split("-");
                        Event newEvent = new Event(inputArray[2], fromToArray[0], fromToArray[1]);
                        if (isDone.equals("1")) {
                            newEvent.markAsDone();
                        }
                        taskList.add(newEvent);
                        break;
                }
                sc.close();
            }
        } catch (FileNotFoundException e) {
            throw new AlisaException("File can't be read!");
        }
        return taskList;
    }

    public void syncFile(TaskList taskList) throws AlisaException {
        try {
            FileWriter fw = new FileWriter(this.filePath);
            fw.write(taskList.toFileString());
            fw.close();
        } catch (IOException e) {
            throw new AlisaException("Couldn't update file!!");
        }

    }
}
