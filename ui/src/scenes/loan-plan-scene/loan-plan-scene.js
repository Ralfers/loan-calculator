import React, { useState } from 'react'

import LoanPlanControls from './components/loan-plan-controls/loan-plan-controls'
import LoanPlan from './components/loan-plan/loan-plan'

export const LoanPlanContext = React.createContext(null)

const LoanPlanScene = props => {
  const [loanPlan, setLoanPlan] = useState({})
  const [currency, setCurrency] = useState('EUR')

  const dataPresent = Object.keys(loanPlan).length !== 0

  return (
    <LoanPlanContext.Provider value={{ loanPlan, setLoanPlan, currency, setCurrency }}>
      <LoanPlanControls />
      {dataPresent && <LoanPlan />}
    </LoanPlanContext.Provider>
  )
}

export default LoanPlanScene
