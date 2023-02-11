import React, { useContext } from 'react'

import { LoanPlanContext } from 'scenes/loan-plan-scene/loan-plan-scene'
import LoanSummaryLabel from './loan-summary-label'
import { formatAmount, formatInterest } from 'utils/formatting'

const containerStyle = {
  paddingTop: '16px',
  paddingBottom: '8px',
  paddingLeft: '24px',
  display: 'flex',
  flexDirection: 'column',
  border: 'solid 0.5px #dee2e6',
  borderBottom: 'none',
  borderRadius: '16px 16px 0px 0px',
}

const LoanPlanSummary = props => {
  const { loanPlan, currency } = useContext(LoanPlanContext)

  return (
    <div style={containerStyle}>
      <LoanSummaryLabel label={'Loaned months'} value={loanPlan.loanMonths} />
      <LoanSummaryLabel label={'Loaned amount'} value={formatAmount(loanPlan.totalPrincipalAmount, currency)} />
      <LoanSummaryLabel label={'Interest rate (yearly)'} value={formatInterest(loanPlan.interest)} />
      <LoanSummaryLabel label={'Interest amount'} value={formatAmount(loanPlan.totalInterestAmount, currency)} />
      <LoanSummaryLabel label={'Total amount'} value={formatAmount(loanPlan.totalAmount, currency)} />
    </div>
  )
}

export default LoanPlanSummary
