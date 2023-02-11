import React, { useContext } from 'react'

import { LoanPlanContext } from 'scenes/loan-plan-scene/loan-plan-scene'
import LoanInstallmentRow from './loan-installment-row'

const LoanTableBody = props => {
  const { loanPlan } = useContext(LoanPlanContext)

  const mapInstallments = () => (
    loanPlan.installments.map((loanInstallment, index) =>
        <LoanInstallmentRow key={index} month={index + 1} loanInstallment={loanInstallment} />
    )
  )

  return (
    <tbody>
      {mapInstallments()}
    </tbody>
  )
}

export default LoanTableBody
