package com.jbirdvegas.mgdorks;

import android.content.Context;
import android.util.Log;
import android.util.Xml;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class GhdbParser {
    private static final String TAG = GhdbParser.class.getSimpleName();
    public static final String GHDB_ID = "ghdb_id";
    public static final String CATEGORY = "category";
    public static final String QUERYSTRING = "querystring";
    public static final String SHORT_DESCRIPTION = "shortDescription";
    public static final String TEXTUAL_DESCRIPTION = "textualDescription";

    public List<Dork> parse(Context context) {
        try (InputStream raw = context.getResources().openRawResource(R.raw.ghdb)) {
            XmlPullParser parser = Xml.newPullParser();
            parser.setFeature(XmlPullParser.FEATURE_PROCESS_NAMESPACES, false);
            parser.setInput(new InputStreamReader(raw, StandardCharsets.UTF_8));
            parser.nextTag();
            return readDorks(parser);
        } catch (XmlPullParserException | IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private List<Dork> readDorks(XmlPullParser parser) throws XmlPullParserException, IOException {
        List<Dork> dorks = new ArrayList<>();
        parser.require(XmlPullParser.START_TAG, null, "searchEngineSignature");
        while (parser.next() != XmlPullParser.END_TAG) {
            if (parser.getEventType() != XmlPullParser.START_TAG) {
                continue;
            }
            String name = parser.getName();
            // Starts by looking for the entry tag
            if (name.equals("signature")) {
                dorks.add(readEntry(parser));
            } else {
                skip(parser);
            }
        }
        return dorks;
    }


    public Dork readEntry(XmlPullParser parser) throws XmlPullParserException, IOException {
        parser.require(XmlPullParser.START_TAG, null, "signature");
        String ghdbId = null;
        String category = null;
        String queryString = null;
        String shortDescription = null;
        String textualDescription = null;
        while (parser.next() != XmlPullParser.END_TAG) {
            if (parser.getEventType() != XmlPullParser.START_TAG) {
                continue;
            }
            String name = parser.getName();
            if (name.equals(GHDB_ID)) {
                ghdbId = readText(parser, GHDB_ID);
            } else if (name.equals(CATEGORY)) {
                category = readText(parser, CATEGORY);
            } else if (name.equals(QUERYSTRING)) {
                queryString = readText(parser, QUERYSTRING);
            } else if (name.equals(SHORT_DESCRIPTION)) {
                shortDescription = readText(parser, SHORT_DESCRIPTION);
            } else if (name.equals(TEXTUAL_DESCRIPTION)) {
                textualDescription = readText(parser, TEXTUAL_DESCRIPTION);
            } else {
                skip(parser);
            }
        }

        return new Dork(ghdbId, category, queryString, shortDescription, textualDescription);
    }

    private String readText(XmlPullParser parser, String requiredTag) throws IOException, XmlPullParserException {
        String text = "";
        parser.require(XmlPullParser.START_TAG, null, requiredTag);
        if (parser.next() == XmlPullParser.TEXT) {
            text = parser.getText();
            parser.nextTag();
        }
        parser.require(XmlPullParser.END_TAG, null, requiredTag);
        return text;
    }

    private void skip(XmlPullParser parser) throws XmlPullParserException, IOException {
        if (parser.getEventType() != XmlPullParser.START_TAG) {
            throw new IllegalStateException();
        }
        Log.d(TAG, "skipping");
        int depth = 1;
        while (depth != 0) {
            switch (parser.next()) {
                case XmlPullParser.END_TAG:
                    depth--;
                    break;
                case XmlPullParser.START_TAG:
                    depth++;
                    break;
            }
        }
    }
}
