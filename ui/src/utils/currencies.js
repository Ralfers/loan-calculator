export const currencies = {
  'EUR': {code: 'EUR', symbol: '€', decimals: 2},
  'USD': {code: 'USD', symbol: '$', decimals: 2},
  'JPY': {code: 'JPY', symbol: '¥', decimals: 0},
}

export const toMinorUnits = (amount, currency) => {
  const decimals = currencies[currency].decimals

  return amount * Math.pow(10, decimals)
}
