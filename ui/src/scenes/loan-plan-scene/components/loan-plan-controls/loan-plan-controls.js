import React, { useState, useContext } from 'react'

import Form from 'react-bootstrap/Form'
import Alert from 'react-bootstrap/Alert';

import { toMinorUnits } from 'utils/currencies'
import { LoanPlanContext } from 'scenes/loan-plan-scene/loan-plan-scene'
import LoanAmountInput from './loan-amount-input'
import LoanPlanInput from './loan-plan-input'
import CalculateButton from './calculate-button'

const containerStyle = {
  display: 'flex',
  flexDirection: 'column',
}

const titleStyle = {
  fontWeight: '600',
  fontSize: '20px',
  marginBottom: '16px',
}

const inputContainerStyle = {
  paddingLeft: '16px',
}

const monthInputStyle = {
  marginTop: '16px',
}

const LoanPlanControls = props => {
  const { setLoanPlan, currency, setCurrency } = useContext(LoanPlanContext)

  const [loanAmount, setLoanAmount] = useState('')
  const [loanMonths, setLoanMonths] = useState('')
  const [errorAlertOpen, setErrorAlertOpen] = useState(false)

  const isPositiveInteger = value => {
    return Number.isInteger(Number(value)) && Number(value) > 0
  }

  const onAmountChange = event => {
    (!event.target.value || isPositiveInteger(event.target.value)) && setLoanAmount(event.target.value)
  }

  const onMonthsChange = event => {
    (!event.target.value || isPositiveInteger(event.target.value)) && setLoanMonths(event.target.value)
  }

  const onCalculate = () => {
    fetch('/loan/housing/plan?' + new URLSearchParams({
        loanedAmount: toMinorUnits(loanAmount, currency),
        loanMonths: loanMonths
    }))
    .then(response => {
      if (response.ok) {
        return response.json()
      }
      throw new Error('Error while fetching loan plan')
    })
    .then(data => {
      setLoanPlan(data)
      setErrorAlertOpen(false)
    })
    .catch(error => setErrorAlertOpen(true))
  }

  return (
    <div style={containerStyle}>
      <Form.Label style={titleStyle}>Select the desired loan criteria:</Form.Label>
      <Alert
          variant='danger'
          show={errorAlertOpen}
          onClose={() => setErrorAlertOpen(false)}
      >
        Calculation request failed
      </Alert>
      <div style={inputContainerStyle}>
        <LoanAmountInput
            label={'Loan amount'}
            value={loanAmount}
            onChange={onAmountChange}
            currency={currency}
            setCurrency={setCurrency}
        />
        <LoanPlanInput
            style={monthInputStyle}
            label={'Loan months'}
            value={loanMonths}
            onChange={onMonthsChange}
        />
        <CalculateButton
            onClick={onCalculate}
            disabled={!loanAmount || !loanMonths}
        />
      </div>
    </div>
  )
}

export default LoanPlanControls
