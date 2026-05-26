package com.forensys.core.command.concrete.go;

import com.forensys.core.command.CommandExitCode;
import com.forensys.core.command.CommandMetadata;
import com.forensys.core.command.CommandOutput;
import com.forensys.core.command.ParsedCommandArgs;
import com.forensys.core.command.TerminalCommand;
import com.forensys.core.command.concrete.go.strategy.GoStrategy;
import com.forensys.core.context.ApplicationContext;

public class GoCommand extends TerminalCommand {

    private final GoStrategyResolver resolver;

    public GoCommand() {
        super(
                new CommandMetadata(
                        "go",
                        "Navigate between directories in the virtual file system",
                        """
                        Usage:
                        go <directory>
                        go <path>
                        go $parent
                        go $root

                        Examples:
                        go documents
                        go $parent/projects
                        go $root/downloads
                        go $parent

                        Notes:
                        - Relative and absolute paths are supported
                        - Use $parent to return to the previous directory
                        """));

        this.resolver = new GoStrategyResolver();
    }

    @Override
    public CommandOutput execute(ParsedCommandArgs args) {
        ApplicationContext applicationContext = ApplicationContext.getInstance();
        if (applicationContext.getPendingExecution() == null) {
            applicationContext.setPendingExecution(() -> {
                return this.execute(args);
            });
        }
        
        GoExecutionContext state = (GoExecutionContext) ApplicationContext.getInstance().getExecutionContext();
        if (state == null) {
            String[] targets = args.positionals().getFirst().split("/");
            state = new GoExecutionContext(targets, 0);
            ApplicationContext.getInstance().setExecutionContext(state);
        }

        while (state.getCurrentIndex() < state.getTargets().length) {
            String target = state.getTargets()[state.getCurrentIndex()];
            GoStrategy strategy = resolver.resolve(target);
            CommandOutput output = strategy.execute();

            if (output.getExitCode() == CommandExitCode.PAUSE) {
                return output;
            }
            
            if (output.getExitCode() == CommandExitCode.FAILURE) {
                return output;
            }

            if (output.getExitCode() == CommandExitCode.SUCCESS) {
                state.setIndex(state.getCurrentIndex() + 1);
            }
        }

        applicationContext.clearExecutionContext();
        applicationContext.clearPendingExecution();
        //TODO: find a better return instead of returning null, maybe add the current output to execution context. Just make a way so the last execution does not overwrites the output in from the previous ones, 
        //NOTE: also cannot just declare output out of the scope of the loop, because it initializes in null and that overwrites the previous output from pending execution
        return null;
    }
}