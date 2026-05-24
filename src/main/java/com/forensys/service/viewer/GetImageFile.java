package com.forensys.service.viewer;

import com.forensys.core.context.ApplicationContext;
import com.forensys.core.filestructure.concrete.ImageFile;

public class GetImageFile {
    public static ImageFile execute() {
        return ApplicationContext.getInstance().getImageFile();
    }
}
