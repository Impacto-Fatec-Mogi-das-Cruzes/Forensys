package com.forensys.core.command.concrete.list;

import java.util.List;

import com.forensys.core.command.CommandExitCode;
import com.forensys.core.command.CommandMetadata;
import com.forensys.core.command.CommandOutput;
import com.forensys.core.command.CommandOutputBuilder;
import com.forensys.core.command.ParsedCommandArgs;
import com.forensys.core.command.TerminalCommand;
import com.forensys.core.command.concrete.list.decorator.EntryFilter;
import com.forensys.core.command.concrete.list.strategy.EntryRenderStrategy;
import com.forensys.core.context.ApplicationContext;
import com.forensys.core.context.PendingOperation;
import com.forensys.core.filestructure.FileSystemEntry;

public class ListCommand extends TerminalCommand {

    public ListCommand() {
        super(new CommandMetadata("list", "list all the children entries in the dir",
                "bla bla bla, flags flags and more flags"));
    }

    @Override
    public CommandOutput execute(ParsedCommandArgs args) {

        ApplicationContext applicationContext = ApplicationContext.getInstance();
        if (applicationContext.getPendingExecution() == null) {
            applicationContext.setPendingExecution(() -> {
                return this.execute(args);
            });
        }

        EntryRenderStrategy renderStrategy = null;

        EntryFilter filter = null;

        List<FileSystemEntry> entries = null;

        ListExecutionContext state = (ListExecutionContext) applicationContext.getExecutionContext();
        if (state == null) {
            renderStrategy = ListStrategyFactory.createRenderStrategy(args);
            filter = ListStrategyFactory.createFilter(args);
            entries = filter.filter(applicationContext.getCurrentDirectory().getChildren());
            applicationContext.setExecutionContext(new ListExecutionContext(renderStrategy, filter, entries));
        }

        if (entries.size() > 75) {
            applicationContext.setPendingOperation(new PendingOperation((answer) -> {
                if (answer.equals("y")) {
                    return buildList();
                } else if (answer.equals("n")) {
                    applicationContext.clearAllExecution();
                    return CommandOutput.builder()
                            .styledText("User canceled operation", "#cbd5e1")
                            .exitCode(CommandExitCode.CANCELED)
                            .build();
                } else {
                    applicationContext.clearAllExecution();
                    return CommandOutput.builder()
                            .styledText("Invalid answer", "#ef4444")
                            .exitCode(CommandExitCode.FAILURE)
                            .build();

                }
            }));
            return CommandOutput.builder()
                .styledText("There are " + entries.size() + " items in this directory.", "#38bdf8").newLine()
                .styledText("Do you really want to print all? [y/n]", "#38bdf8")
                .exitCode(CommandExitCode.PAUSE)
                .build();
        }

        return buildList();
    }

    private CommandOutput buildList() {
        ApplicationContext applicationContext = ApplicationContext.getInstance();
        ListExecutionContext state = (ListExecutionContext) applicationContext.getExecutionContext();

        EntryRenderStrategy renderStrategy = state.getRenderStrategy();
        List<FileSystemEntry> entries = state.getEntries();

        CommandOutputBuilder outputBuilder = CommandOutput.builder()
                .styledText("In the " + applicationContext.getCurrentDirectory().getMetadata().name() + " directory you have:", "#ffffff")
                .newLine();

        renderStrategy.renderHeader(outputBuilder);

        for (FileSystemEntry entry : entries) {
            renderStrategy.renderEntry(outputBuilder, entry);
        }

        applicationContext.clearAllExecution();
        return outputBuilder
                .exitCode(CommandExitCode.SUCCESS)
                .build();
    }

}
