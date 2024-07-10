import { CurrencyPostfixPipe } from './currency-postfix.pipe';

describe('CurrencyPostfixPipe', () => {
  it('create an instance', () => {
    const pipe = new CurrencyPostfixPipe();
    expect(pipe).toBeTruthy();
  });
});
