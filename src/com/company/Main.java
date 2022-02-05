package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {

    class Portfolio {
        String porfolioName;
        String positionName;
        Date startDate;
        Date endDate;
        boolean positionOpen;

        public Portfolio(String portfolioName, String positionName, Date startDate, Date endDate, boolean positionOpen) {
            this.porfolioName = portfolioName;
            this.positionName = positionName;
            this.startDate = startDate;
            this.endDate = endDate;
            this.positionOpen = false;
        }
    }

    public static void main(String[] args) throws IOException {
        InputStreamReader reader = new InputStreamReader(System.in, StandardCharsets.UTF_8);
        BufferedReader in = new BufferedReader(reader);

        //String dateString = in.readLine();

        //System.out.println(dateString);
        Collection<Portfolio> portfolios = Main.getPortfolios(in);

        //Main.processInput(dateString, portfolios);
    }

    public static void processInput(String dateString, Collection<Portfolio> portfolios) {
        // Access your code here. Feel free to create other classes as required

    }

    private static Collection<Portfolio> getPortfolios(BufferedReader in) throws IOException {
        String startDate = "";
        String endDate = "";
        String portfolioName = "";
        String portfolioPositionName = "";
        Collection<Portfolio> portfolios = new ArrayList<Portfolio>();
        // expand this class to read the portfolios. Feel free to create other classes as required


        String line;
        while ((line = in.readLine()) != null) {
            Date checkDate = new Date();
            //Separate the lines by portfoliopositions
            String portfolioPositions[] = line.split(",");
            for (int i = 0; i < portfolioPositions.length; i++) {
                //This will split the portfolio information to position name, starting date and ending date.
                String portfolioInformation = portfolioPositions[i];
                String portfolioDetails[] = portfolioInformation.split(":");
                //First portfolio position will contain the name of the portfolio as well as the position name s
                // separated by a | hence should be a special case
                    String portfolioNameAndPosition = portfolioDetails[0];
                    int portfolioNameIndex = portfolioNameAndPosition.indexOf("|");
                    portfolioName = portfolioNameAndPosition.substring(0, portfolioNameIndex);
                    //Get the positionName by getting the substring that comes after the index.
                    portfolioPositionName = portfolioNameAndPosition.substring(portfolioNameIndex + 1);
                startDate = portfolioDetails[1];
                endDate = portfolioDetails[2];


                //String portfolioPosition=
                //String segments[] = line.split(":");
                //System.out.println(Arrays.toString(portfolioPositions));
            }
            System.out.println(portfolioName);
            System.out.println(portfolioPositionName);
            System.out.println(startDate);
            System.out.println(endDate);
        }
        return portfolios;
    }
}
