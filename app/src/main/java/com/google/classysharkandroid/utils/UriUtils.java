/*
 * Copyright 2015 Google, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.google.classysharkandroid.utils;

import android.content.Context;
import android.net.Uri;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class UriUtils {

    public static InputStream getStreamFromUri(Context context, Uri uriFromIntent) throws FileNotFoundException {
        return context.getContentResolver().openInputStream(uriFromIntent);
    }

    public static boolean isAttach(Uri uriFromIntent) {
        return (uriFromIntent != null) && (uriFromIntent.getScheme().contains("content"));
    }

    public static String pathUriCache(Context context, Uri uri, String nCache) {
        File f = new File(context.getCacheDir(), nCache);
        try {
            FileOutputStream fos = new FileOutputStream(f);
            InputStream is = context.getContentResolver().openInputStream(uri);
            if (is == null) return null;
            IOUtils.copyLarge(is, fos);
            return f.getPath();
        } catch (IOException e) {
            return null;
        }

    }
}
