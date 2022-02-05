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

    static class Portfolio {
        private String portfolioName;
        private String positionName;
        private String startDate;
        private String endDate;
        private boolean positionOpen;

        public Portfolio(String portfolioName, String portfolioPositionName, String startDate, String endDate) {
            this.portfolioName = portfolioName;
            this.positionName = portfolioPositionName;
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
        boolean portfolioInformationRetrieved = false;
        Collection<Portfolio> portfolios = new ArrayList<Portfolio>();
        // expand this class to read the portfolios. Feel free to create other classes as required
        String line;
        while ((line = in.readLine()) != null) {
            //Date checkDate = new Date();
            //Separate the lines by portfolio positions
            String portfolioPositions[] = line.split(",");
            for (int i = 0; i < portfolioPositions.length; i++) {
                //This will split the portfolio information to position name, starting date and ending date.
                String portfolioInformation = portfolioPositions[i];
                String portfolioDetails[] = portfolioInformation.split(":");
                for (int k = 0; k < portfolioDetails.length; k++) {
                    portfolioInformationRetrieved = false;

                    switch (k) {
                        case 0:
                            //First portfolio position will contain the name of the portfolio as well as the position name
                            // separated by a | hence should be a special case
                            String portfolioNameAndPosition = portfolioDetails[k];
                            int portfolioNameIndex = portfolioNameAndPosition.indexOf("|");
                            if (portfolioNameIndex != -1) {
                                portfolioName = portfolioNameAndPosition.substring(0, portfolioNameIndex);
                                //Get the positionName by getting the substring that comes after the index.
                                portfolioPositionName = portfolioNameAndPosition.substring(portfolioNameIndex + 1);
                            } else {
                                //Other information just contain the portfiliopositions
                                portfolioPositionName = portfolioDetails[k];
                            }
                            break;
                        case 1:
                            startDate = portfolioDetails[k];
                            break;
                        case 2:
                            endDate = portfolioDetails[k];
                            portfolioInformationRetrieved = true;

                            break;
                    }
                    //Once you have retrieved all the portfolio information make a portfolio object
                    if (portfolioInformationRetrieved) {
                        Portfolio currentPortfolio = new Portfolio(portfolioName, portfolioPositionName, startDate, endDate);
                        portfolios.add(currentPortfolio);
                    }
                }
            }
        }
        return portfolios;
    }
}
