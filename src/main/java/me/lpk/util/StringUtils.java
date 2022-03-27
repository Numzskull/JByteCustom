package me.lpk.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringUtils {

    /**
     * Replaces strings with old references with ones with updated references.
     *
     * @param orig
     * @param oldStr
     * @param newStr
     * @return
     */
    public static String replace(String orig, String oldStr, String newStr) {
        StringBuffer sb = new StringBuffer(orig);
        while (contains(sb.toString(), oldStr)) {
            if (orig.contains("(") && orig.contains(";")) {
                // orig is most likely a method desc
                int start = sb.indexOf("L" + oldStr) + 1;
                int end = sb.indexOf(oldStr + ";") + oldStr.length();
                if (start > -1 && end <= orig.length()) {
                    sb.replace(start, end, newStr);
                } else {
                    System.err.println("REPLACE FAIL: (" + oldStr + ") - " + orig);
                    break;
                }
            } else if (orig.startsWith("L") && orig.endsWith(";")) {
                // orig is most likely a class desc
                if (orig.substring(1, orig.length() - 1).equals(oldStr)) {
                    sb.replace(1, orig.length() - 1, newStr);
                }
            } else {
                // Dunno
                if (orig.equals(oldStr)) {
                    sb.replace(0, sb.length(), newStr);
                } else {
                    // This shouldn't happen.
                    System.err.println("FUCK: (" + sb.toString() + ") - " + oldStr + ":" + newStr);
                    break;
                }
            }
        }
        return sb.toString();
    }

    public static boolean contains(String orig, String check) {
        if (orig.contains(check)) {
            String regex = "([L]" + check.replace("/", "\\/") + "{1}[;])";
            if (orig.contains("(") && orig.contains(";")) {
                return orig.matches(regex);
            } else if (orig.startsWith("L") && orig.endsWith(";") && orig.substring(1, orig.length() - 1).equals(check)) {
                return true;
            } else if (orig.equals(check)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Checks if a given string is a link.
     * <p>
     * TODO: Make this not shitty.
     *
     * @param input
     * @return
     */
    public static boolean isLink(String input) {
        String regex = "(?i)\\b((?:https?:(?:/{1,3}|[a-z0-9%])|[a-z0-9.\\-]+[.](?:com|net|org|edu|gov|mil|aero|asia|biz|cat|coop|info|int|jobs|mobi|museum|name|post|pro|tel|travel|xxx|ac|ad|ae|af|ag|ai|al|am|an|ao|aq|ar|as|at|au|aw|ax|az|ba|bb|bd|be|bf|bg|bh|bi|bj|bm|bn|bo|br|bs|bt|bv|bw|by|bz|ca|cc|cd|cf|cg|ch|ci|ck|cl|cm|cn|co|cr|cs|cu|cv|cx|cy|cz|dd|de|dj|dk|dm|do|dz|ec|ee|eg|eh|er|es|et|eu|fi|fj|fk|fm|fo|fr|ga|gb|gd|ge|gf|gg|gh|gi|gl|gm|gn|gp|gq|gr|gs|gt|gu|gw|gy|hk|hm|hn|hr|ht|hu|id|ie|il|im|in|io|iq|ir|is|it|je|jm|jo|jp|ke|kg|kh|ki|km|kn|kp|kr|kw|ky|kz|la|lb|lc|li|lk|lr|ls|lt|lu|lv|ly|ma|mc|md|me|mg|mh|mk|ml|mm|mn|mo|mp|mq|mr|ms|mt|mu|mv|mw|mx|my|mz|na|nc|ne|nf|ng|ni|nl|no|np|nr|nu|nz|om|pa|pe|pf|pg|ph|pk|pl|pm|pn|pr|ps|pt|pw|py|qa|re|ro|rs|ru|rw|sa|sb|sc|sd|se|sg|sh|si|sj|Ja|sk|sl|sm|sn|so|sr|ss|st|su|sv|sx|sy|sz|tc|td|tf|tg|th|tj|tk|tl|tm|tn|to|tp|tr|tt|tv|tw|tz|ua|ug|uk|us|uy|uz|va|vc|ve|vg|vi|vn|vu|wf|ws|ye|yt|yu|za|zm|zw)/)(?:[^\\s()<>{}\\[\\]]+|\\([^\\s()]*?\\([^\\s()]+\\)[^\\s()]*?\\)|\\([^\\s]+?\\))+(?:\\([^\\s()]*?\\([^\\s()]+\\)[^\\s()]*?\\)|\\([^\\s]+?\\)|[^\\s`!()\\[\\]{};:'\".,<>?«»“”‘’])|(?:(?<!@)[a-z0-9]+(?:[.\\-][a-z0-9]+)*[.](?:com|net|org|edu|gov|mil|aero|asia|biz|cat|coop|info|int|jobs|mobi|museum|name|post|pro|tel|travel|xxx|ac|ad|ae|af|ag|ai|al|am|an|ao|aq|ar|as|at|au|aw|ax|az|ba|bb|bd|be|bf|bg|bh|bi|bj|bm|bn|bo|br|bs|bt|bv|bw|by|bz|ca|cc|cd|cf|cg|ch|ci|ck|cl|cm|cn|co|cr|cs|cu|cv|cx|cy|cz|dd|de|dj|dk|dm|do|dz|ec|ee|eg|eh|er|es|et|eu|fi|fj|fk|fm|fo|fr|ga|gb|gd|ge|gf|gg|gh|gi|gl|gm|gn|gp|gq|gr|gs|gt|gu|gw|gy|hk|hm|hn|hr|ht|hu|id|ie|il|im|in|io|iq|ir|is|it|je|jm|jo|jp|ke|kg|kh|ki|km|kn|kp|kr|kw|ky|kz|la|lb|lc|li|lk|lr|ls|lt|lu|lv|ly|ma|mc|md|me|mg|mh|mk|ml|mm|mn|mo|mp|mq|mr|ms|mt|mu|mv|mw|mx|my|mz|na|nc|ne|nf|ng|ni|nl|no|np|nr|nu|nz|om|pa|pe|pf|pg|ph|pk|pl|pm|pn|pr|ps|pt|pw|py|qa|re|ro|rs|ru|rw|sa|sb|sc|sd|se|sg|sh|si|sj|Ja|sk|sl|sm|sn|so|sr|ss|st|su|sv|sx|sy|sz|tc|td|tf|tg|th|tj|tk|tl|tm|tn|to|tp|tr|tt|tv|tw|tz|ua|ug|uk|us|uy|uz|va|vc|ve|vg|vi|vn|vu|wf|ws|ye|yt|yu|za|zm|zw)\\b/?(?!@)))";

        return input.contains("/") && input.contains(".") && input.matches(regex);
    }

    /**
     * Checks if a description is just a primitive.
     *
     * @param description
     * @return
     */
    public static boolean isPrimitive(String description) {
        String x = asmTrim(description);
        if (x.length() == 0) {
            return true;
        } else if (x.equals("Z") || x.equals("J") || x.equals("I") || x.equals("F") || x.equals("D") || x.equals("C") || x.equals("T") || x.equals("G")) {
            return true;
        }
        return false;
    }

    /**
     * Checks if a given string is an IP address.
     *
     * @param input
     * @return
     */
    public static boolean isIP(String input) {
        String regex = "(?:[0-9]{1,3}\\.){3}[0-9]{1,3}";
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(input);
        String ss = input.replace(".", "");
        if (m.find() && isNumeric(ss) && (input.length() - ss.length() > 2)) {
            return true;
        }
        return false;
    }

    /**
     * Checks if a string is a number
     *
     * @param s
     * @return
     */
    public static boolean isNumeric(String s) {
        try {
            Double.parseDouble(s);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public static String asmTrim(String s) {
        // TODO: should this remove primitives?
        // (?=([L;()\/\[IDFJBZV]))
        return s.replaceAll("(?=([L;()\\/\\[IDFJBZV]))", "");
    }
}