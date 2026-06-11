package org.jhotdraw.samples.svg;

import java.io.File;
import java.net.URI;
import java.util.Locale;

final class SVGFileNamingStrategy {

    private SVGFileNamingStrategy() {
    }

    static URI appendSVGExtension(URI uri) {
        if (uri == null) {
            return uri;
        }
        File file = new File(uri);
        String path = file.getPath();
        String lowerPath = path.toLowerCase(Locale.ENGLISH);
        if (lowerPath.endsWith(".svg") || lowerPath.endsWith(".svgz")) {
            return uri;
        }
        return new File(path + ".svg").toURI();
    }
}
