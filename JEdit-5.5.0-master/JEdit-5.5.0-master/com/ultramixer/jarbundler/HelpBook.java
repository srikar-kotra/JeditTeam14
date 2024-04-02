 /*
 * Copyright (c) 2015, UltraMixer Digital Audio Solutions <info@ultramixer.com>, Seth J. Morabito <sethm@loomcom.com>
 * All rights reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 *
 */

package com.ultramixer.jarbundler;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.io.File;

public class HelpBook {

    private String folderName;
    private String locale;
    private String name;
    private final List<File> fileList;
    private final Set<File> fileSet;

    public HelpBook() {
        this.fileList = new ArrayList<>();
        this.fileSet = new HashSet<>();
    }

    // Help Book name
    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    // Help Book folder name
    public void setFolderName(String folderName) {
        this.folderName = folderName;
    }

    public String getFolderName() {
        return folderName;
    }

    // Help Book locale
    public void setLocale(String locale) {
        this.locale = locale;
    }

    public String getLocale() {
        return locale;
    }

    // Help Book files as a list of files
    public void addFile(File file) {
        fileList.add(file);
    }

    public List<File> getFiles() {
        return fileList;
    }

    // Help Book files as a set of files
    public void addFile(File file) {
        fileSet.add(file);
    }

    public Set<File> getFiles() {
        return fileSet;
    }

    // Add FileList
    public void addFileList(FileList list) {
        list.addFile(file);
    }

    public FileList getFileLists() {
        return new FileList();
    }

    // Add FileSet
    public void addFileSet(FileSet set) {
        set.addFile(file);
    }

    public FileSet getFileSets() {
        return new FileSet();
    }
}

public class FileList {
    private List<File> files;

    public void addFile(File file) {
        files.add(file);
    }
}

public class FileSet {
    private Set<File> files;

    public void addFile(File file) {
        files.add(file);
    }
}
