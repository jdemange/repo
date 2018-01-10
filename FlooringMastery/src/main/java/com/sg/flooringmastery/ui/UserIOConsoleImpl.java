/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.flooringmastery.ui;

import java.util.Scanner;

/**
 *
 * @author jjd
 */
public class UserIOConsoleImpl implements UserIO{

    public final Scanner SC = new Scanner(System.in);

    @Override
    public void print(String message) {
        System.out.println(message);
    }

    @Override
    public double readDouble(String prompt) {
        boolean check = false;
        String stringValue = "0";
        double value = 0;

        while (check == false) {
            System.out.println(prompt);
            while (SC.hasNextLine()) {
                if (SC.hasNextDouble() == true) {

                    do {
                        stringValue = SC.nextLine();
                        stringValue = stringValue.replaceAll("[\t+,+]", "");
                    } while ("".equals(stringValue));

                    value = Double.parseDouble(stringValue);
                    check = true;
                    break;
                } else {
                    SC.nextLine();

                }
                break;
            }
        }

        return value;
    }

    @Override
    public double readDouble(String prompt, double min, double max) {
        boolean check = false;
        String stringValue = "0";
        double value = 0;

        while (check == false) {
            System.out.println(prompt);
            while (SC.hasNextLine()) {
                if (SC.hasNextDouble() == true) {

                    do {
                        stringValue = SC.nextLine();
                        stringValue = stringValue.replaceAll("[\t+,+]", "");
                    } while ("".equals(stringValue));

                    value = Double.parseDouble(stringValue);
                    if (value < min || value > max) {
                        break;
                    }
                    check = true;
                    break;
                } else {
                    SC.nextLine();

                }
                break;
            }
        }

        return value;
    }

    @Override
    public float readFloat(String prompt) {
        boolean check = false;
        String stringValue = "0";
        float value = 0;

        while (check == false) {
            System.out.println(prompt);
            while (SC.hasNextLine()) {
                if (SC.hasNextFloat() == true) {

                    do {
                        stringValue = SC.nextLine();
                        stringValue = stringValue.replaceAll("[\t+,+]", "");
                    } while ("".equals(stringValue));

                    value = Float.parseFloat(stringValue);
                    check = true;
                    break;
                } else {
                    SC.nextLine();

                }
                break;
            }
        }

        return value;
    }

    @Override
    public float readFloat(String prompt, float min, float max) {
        boolean check = false;
        String stringValue = "0";
        float value = 0;

        while (check == false) {
            System.out.println(prompt);
            while (SC.hasNextLine()) {
                if (SC.hasNextFloat() == true) {

                    do {
                        stringValue = SC.nextLine();
                        stringValue = stringValue.replaceAll("[\t+,+]", "");
                    } while ("".equals(stringValue));

                    value = Float.parseFloat(stringValue);
                    if (value < min || value > max) {
                        break;
                    }
                    check = true;
                    break;
                } else {
                    SC.nextLine();

                }
                break;
            }
        }

        return value;

    }

    @Override
    public int readInt(String prompt) {
        boolean check = false;
        String stringValue = "0";
        int value = 0;

        while (check == false) {
            System.out.println(prompt);
            while (SC.hasNextLine()) {
                if (SC.hasNextInt() == true) {

                    do {
                        stringValue = SC.nextLine();
                        stringValue = stringValue.replaceAll("[\t+,+]", "");
                    } while ("".equals(stringValue));

                    value = Integer.parseInt(stringValue);
                    check = true;
                    break;
                } else {
                    SC.nextLine();

                }
                break;
            }
        }

        return value;
    }

    @Override
    public int readInt(String prompt, int min, int max) {
        boolean check = false;
        String stringValue = "0";
        int value = 0;

        while (check == false) {
            System.out.println(prompt);
            while (SC.hasNextLine()) {
                if (SC.hasNextInt() == true) {

                    do {
                        stringValue = SC.nextLine();
                        stringValue = stringValue.replaceAll("[\t+,+]", "");
                    } while ("".equals(stringValue));

                    value = Integer.parseInt(stringValue);
                    if (value < min || value > max) {
                        break;
                    }
                    check = true;
                    break;
                } else {
                    SC.nextLine();

                }
                break;
            }
        }

        return value;
    }

    @Override
    public long readLong(String prompt) {
        boolean check = false;
        String stringValue = "0";
        long value = 0;

        while (check == false) {
            System.out.println(prompt);
            while (SC.hasNextLine()) {
                if (SC.hasNextLong() == true) {

                    do {
                        stringValue = SC.nextLine();
                        stringValue = stringValue.replaceAll("[\t+,+]", "");
                    } while ("".equals(stringValue));

                    value = Long.parseLong(stringValue);
                    check = true;
                    break;
                } else {
                    SC.nextLine();

                }
                break;
            }
        }

        return value;
    }

    @Override
    public long readLong(String prompt, long min, long max) {
        boolean check = false;
        String stringValue = "0";
        long value = 0;

        while (check == false) {
            System.out.println(prompt);
            while (SC.hasNextLine()) {
                if (SC.hasNextLong() == true) {

                    do {
                        stringValue = SC.nextLine();
                        stringValue = stringValue.replaceAll("[\t+,+]", "");
                    } while ("".equals(stringValue));

                    value = Long.parseLong(stringValue);
                    if (value < min || value > max) {
                        break;
                    }
                    check = true;
                    break;
                } else {
                    SC.nextLine();

                }
                break;
            }
        }

        return value;
    }

    @Override
    public String readString(String prompt) {
        System.out.println(prompt);
        String value = SC.nextLine();
        return value;
    }

    @Override
    public void printSameLine(String message) {
        System.out.print(message);
    }
}
