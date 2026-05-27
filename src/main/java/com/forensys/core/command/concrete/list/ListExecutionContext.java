package com.forensys.core.command.concrete.list;

import java.util.List;

import com.forensys.core.command.concrete.list.decorator.EntryFilter;
import com.forensys.core.command.concrete.list.strategy.EntryRenderStrategy;
import com.forensys.core.context.ExecutionContext;
import com.forensys.core.filestructure.FileSystemEntry;

public class ListExecutionContext implements ExecutionContext {
        private EntryRenderStrategy renderStrategy;
        private EntryFilter filter;
        private List<FileSystemEntry> entries;

        public ListExecutionContext(EntryRenderStrategy renderStrategy, EntryFilter filter,
                List<FileSystemEntry> entries) {
            this.renderStrategy = renderStrategy;
            this.filter = filter;
            this.entries = entries;
        }

        public EntryRenderStrategy getRenderStrategy() {
            return renderStrategy;
        }

        public EntryFilter getFilter() {
            return filter;
        }

        public List<FileSystemEntry> getEntries() {
            return entries;
        }
}
