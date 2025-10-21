public class StringStuff {

    static String changeFormat(String date){
        
        //Gets index of first slash
        int firstSlash = date.indexOf('/');
        //Gets index of second slash starting at index after first slash (to avoid detecting the first slash again it it first index + 1)
        int secondSlash = date.indexOf('/', firstSlash + 1);

        // substring between [0] & [firstSlash - 1]
        String month = date.substring(0, firstSlash);
        // substring between [firstSlash + 1] & [secondSlash - 1]
        String day = date.substring(firstSlash + 1, secondSlash);
        // substring from [secondSlash + 1] to end
        String year = date.substring(secondSlash + 1);

        String formattedDate = day + '-' + month + '-' + year;

        return formattedDate;
    }

    static String eliminateDashes(String ssn){
        
        // Creates array where every element is the substring occurring between every '-'
        String[] split = ssn.split("-");
        // rebuilds string from each string in array
        String formattedSsn = split[0] + split[1] + split[2];

        return formattedSsn;
    }

    static String scroll(String scroll){
        // Returns string containing all characters from index [1]+ and appends the first character to the end
        return scroll.substring(1) + scroll.substring(0, 1);
    }

    static String extract(String extract, String extract1, String extract2){

        /* get middle 3 characters by getting substring between length / 3 (integer division truncates the decimal part) & 
         * length - (length / 3)
         * since we're searching for middle third it can be thought of as finding 2/3's {search between 1/3 and (3/3 - (1/3))}
        */
        String mid = extract.substring(extract.length() / 3, extract.length() - (extract.length() / 3));
        String mid1 = extract1.substring(extract1.length() / 3, extract1.length() - (extract1.length() / 3));
        String mid2 = extract2.substring(extract2.length() / 3, extract2.length() - (extract2.length() / 3));
        
        String combo = mid + mid1 + mid2;

        return combo;
    }

    static String convertName(String name){

        //Trims leading and tailing empty characters
        String trim = name.trim();
        //Finds first occurance of a space
        int firstSpace = trim.indexOf(" ");

        //Finds first name by taking substring between [0] and [firstSpace]
        String firstName = trim.substring(0, firstSpace);
        //Searched from firstSpace to end
        String lastName = trim.substring(firstSpace + 1);

        //Formats name and gets rid of any extra empty spaces when creating substrings
        String formattedName = lastName.trim() + ", " + firstName.trim();

        return formattedName;
    }
}