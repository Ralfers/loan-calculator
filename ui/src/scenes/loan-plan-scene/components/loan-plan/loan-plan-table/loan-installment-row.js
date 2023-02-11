import React, { useContext } from 'react'

import { LoanPlanContext } from 'scenes/loan-plan-scene/loan-plan-scene'
import { formatAmount } from 'utils/formatting'

const LoanInstallmentRow = props => {
  const { month, loanInstallment } = props
  const { currency } = useContext(LoanPlanContext)

  return (
    <tr>
      <td>{month}</td>
      <td>{formatAmount(loanInstallment.amount, currency)}</td>
      <td>{formatAmount(loanInstallment.interestAmount, currency)}</td>
      <td>{formatAmount(loanInstallment.principalAmount, currency)}</td>
      <td>{formatAmount(loanInstallment.remainingPrincipalAmount, currency)}</td>
    </tr>
  )
}

export default LoanInstallmentRow
