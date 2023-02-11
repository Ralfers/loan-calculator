import React from 'react'

import Button from 'react-bootstrap/Button'

const buttonStyle = {
  marginTop: '16px',
}

const CalculateButton = props => {
  const { onClick, disabled } = props

  return (
    <Button
        style={buttonStyle}
        variant='dark'
        size='sm'
        onClick={onClick}
        disabled={disabled}
    >
        Calculate
    </Button>
  )
}

export default CalculateButton
