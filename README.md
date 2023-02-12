# Loan Calculation Application

## Backend

The backend is a Spring Boot application which exposes the following API for housing loan calculation:

- `GET /loan/housing/plan?loanedAmount=<loanedAmount>&loanMonths=<loanMonths>` - calculates an equated monthly installment (EMI) loan plan with housing interest `3.5%`:
    - loanedAmount - amount loaned, must be a positive integer;
    - loanMonths - months to repay the loan.

All amounts by this application are handled in **minor units**, e.g., the amount `EUR 215.46` must be sent as `21546`.  

An insomnia import script is provided by `Insomnia_2023-02-12.json`, which can be used to test the API.

## GUI

The graphical user interface is a React application located under the `ui` directory, which needs to be set-up as follows:

- install npm
- under the `ui` directory run:
    - `npm i` to install npm dependencies;
    - `npm run build` to compile the React code and copy the required static files under the Spring Boot backend application;
    - alternatively, can run `npm run start` to deploy the GUI on a Node server for development purposes with auto-reload enabled (works with a running Spring Boot backend).
