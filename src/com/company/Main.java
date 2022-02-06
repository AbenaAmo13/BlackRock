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

        @Override
        public String toString() {
            return portfolioName + ", " + positionName + ", " + startDate + ", " + endDate + ", " + positionOpen;
        }
    }

    public static void main(String[] args) throws IOException {
        InputStreamReader reader = new InputStreamReader(System.in, StandardCharsets.UTF_8);
        BufferedReader in = new BufferedReader(reader);
        String dateString = in.readLine();
        Collection<Portfolio> portfolios = Main.getPortfolios(in);
        Main.processInput(dateString, portfolios);
    }

    public static void processInput(String dateString, Collection<Portfolio> portfolios) {
        // Access your code here. Feel free to create other classes as required
        System.out.println("Have reached here");
        System.out.println("Getting right portfolio from" + Arrays.toString(portfolios.toArray()));
    }

    private static Collection<Portfolio> getPortfolios(BufferedReader in) {
        Collection<Portfolio> retrievedPortfolios = new ArrayList<>();
        try {
            String startDate = "";
            String endDate = "";
            String portfolioName = "";
            String portfolioPositionName = "";
            int numberOfPortfolios = 0;
            boolean receivedAllPortfolios = false;
            // expand this class to read the portfolios. Feel free to create other classes as required
            String line = null;
            while ((line = in.readLine()) != null && !line.equals("")) {
                //Separate the lines by portfolio positions
                String portfolioPositions[] = line.split(",");
                for (int i = 0; i < portfolioPositions.length; i++) {
                    //This will split the portfolio information to position name, starting date and ending date.
                    String portfolioInformation = portfolioPositions[i];
                    String portfolioDetails[] = portfolioInformation.split(":");
                    for (int k = 0; k < portfolioDetails.length; k++) {
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
                                    //Other information just contain the portfolio positions
                                    portfolioPositionName = portfolioDetails[k];
                                }
                                break;
                            case 1:
                                startDate = portfolioDetails[k];
                                break;
                            case 2:
                                endDate = portfolioDetails[k];
                                break;
                        }
                    }
                    //Once you have retrieved all the information place them here.
                    Portfolio currentPortfolio = new Portfolio(portfolioName, portfolioPositionName, startDate, endDate);
                    //Add the portfolio object to the collection that is to be returned.
                    retrievedPortfolios.add(currentPortfolio);
                }
            }
            //System.out.println("Gets out of the loop here");
            System.out.println(Arrays.toString(retrievedPortfolios.toArray()));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return retrievedPortfolios;
    }
}
