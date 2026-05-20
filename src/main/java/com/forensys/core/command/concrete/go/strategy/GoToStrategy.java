package com.forensys.core.command.concrete.go.strategy;

import java.util.Optional;

import com.forensys.common.command.ExecutionStrategy;
import com.forensys.common.exception.InvalidDirectoryMovement;
import com.forensys.core.command.CommandExitCode;
import com.forensys.core.command.CommandOutput;
import com.forensys.core.command.ParsedCommandArgs;
import com.forensys.core.context.ApplicationContext;
import com.forensys.core.filestructure.FileSystemEntry;
import com.forensys.core.filestructure.concrete.Directory;

public class GoToStrategy implements ExecutionStrategy {

    @Override
    public CommandOutput execute(ParsedCommandArgs arguments) {
        String target = arguments.positionals().get(0);
        ApplicationContext applicationContext = ApplicationContext.getInstance();
        try {
            Optional<FileSystemEntry> entry = applicationContext.getCurrentDirectory()
                    .getChildren()
                    .stream()
                    .filter(obj -> obj.getMetadata().name().equals(target))
                    .findFirst();

            if (entry.isEmpty() || !(entry.get() instanceof Directory directory)) {
                throw new InvalidDirectoryMovement("Directory not found, please choose a valid directory");
            }
            applicationContext.setCurrentDirectory(directory);
        } catch (InvalidDirectoryMovement e) {
            return CommandOutput.builder()
                    .text(e.getMessage())
                    .exitCode(CommandExitCode.FAILURE)
                    .build();
        } catch (Exception e) {
            System.err.println(e);
        }
        return CommandOutput.builder()
                .text("Current location was changed to " + target)
                .exitCode(CommandExitCode.SUCCESS)
                .build();
    }

}
