import React from 'react'

import Form from 'react-bootstrap/Form'

const labelStyle = {
  marginRight: '8px',
  fontWeight: '100',
}

const LoanSummaryLabel = props => {
  const { label, value } = props

  return (
    <div>
      <Form.Label style={labelStyle}>{label + ':'}</Form.Label>
      <Form.Label>{value}</Form.Label>
    </div>
  )
}

export default LoanSummaryLabel
