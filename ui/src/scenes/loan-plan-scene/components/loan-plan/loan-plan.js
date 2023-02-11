import React from 'react'

import Form from 'react-bootstrap/Form'

import LoanPlanSummary from './loan-plan-summary/loan-plan-summary'
import LoanPlanTable from './loan-plan-table/loan-plan-table'

const containerStyle = {
  marginTop: '48px',
  width: '1300px',
}

const titleStyle = {
  fontWeight: '600',
  fontSize: '20px',
  marginBottom: '16px',
}

const LoanPlan = props => {
  const { loanPlan } = props

  return (
    <div style={containerStyle}>
      <Form.Label style={titleStyle}>Calculated payback plan:</Form.Label>
      <LoanPlanSummary />
      <LoanPlanTable />
    </div>
  )
}

export default LoanPlan
