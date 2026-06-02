package com.forensys.core.command.concrete.chat;

import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.forensys.core.command.CommandOutput;
import com.forensys.core.command.CommandOutputBuilder;
import com.forensys.core.command.ParsedCommandArgs;

public class ListSubCommand implements ChatSubCommand {

    @Override
    public CommandOutput execute(ParsedCommandArgs args) {
        List<String> files = new ArrayList<>();
        URL url = getClass().getResource("/chats/");

        if (url != null) {
            try {
                Path path = Paths.get(url.toURI());
                files = Files.list(path)
                        .map(Path::getFileName)
                        .map(Path::toString)
                        .collect(Collectors.toList());
            } catch (Exception e) {
                System.err.println(e);
            }
        }

        String pattern = args.positionals().size() > 1 
            ? args.positionals().get(1) 
            : "*";

        List<String> filteredFiles = files.stream()
                .filter(file -> matchesPattern(file, pattern))
                .sorted()
                .collect(Collectors.toList());

        CommandOutputBuilder outputBuilder = CommandOutput.builder().styledText("Available contact lists: ", "#cbd5e1").newLine();

        if (filteredFiles.isEmpty()) {
            outputBuilder.styledText("\tNo contacts found matching pattern: " + pattern, "#ef4444").newLine();
        } else {
            for (int i = 0; i < filteredFiles.size(); i++) {
                String file = filteredFiles.get(i);
                outputBuilder.text("\t" + i + 1 + ". " + file.substring(0, file.lastIndexOf("."))).newLine();
            }
        }

        return outputBuilder.build();
    }

    private boolean matchesPattern(String filename, String pattern) {
        String nameWithoutExt = filename.substring(0, filename.lastIndexOf("."));
        
        String regex = pattern
                .replace(".", "\\.")
                .replace("*", ".*")
                .replace("?", ".");
        
        return nameWithoutExt.matches("(?i)" + regex);
    }
}
