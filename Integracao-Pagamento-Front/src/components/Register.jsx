import { useState } from 'react'
import { Link } from 'react-router-dom'
import '../css/Register.css'
import JsonMessage from '../JsonMessage.json'

/* ---------- helpers ---------- */
function onlyDigits(value) {
    return value.replace(/\D/g, '')
}

function isValidCpf(cpf) {
    const digits = onlyDigits(cpf)
    if (digits.length !== 11) return false
    if (/^(\d)\1+$/.test(digits)) return false
    let sum = 0
    for (let i = 0; i < 9; i++) sum += Number(digits[i]) * (10 - i)
    let check1 = (sum * 10) % 11
    if (check1 === 10) check1 = 0
    if (check1 !== Number(digits[9])) return false
    sum = 0
    for (let i = 0; i < 10; i++) sum += Number(digits[i]) * (11 - i)
    let check2 = (sum * 10) % 11
    if (check2 === 10) check2 = 0
    return check2 === Number(digits[10])
}

function isValidEmail(email) {
    return /^[^\s@]+@[^\s@]+\.[^\s@]{2,}$/.test(email)
}

function passwordScore(password) {
    let score = 0
    if (password.length >= 8) score++
    if (password.length >= 12) score++
    if (/[A-Z]/.test(password)) score++
    if (/[a-z]/.test(password)) score++
    if (/\d/.test(password)) score++
    if (/[^A-Za-z0-9]/.test(password)) score++
    return Math.min(score, 4)
}

const strengthLabel = ['vazia', 'fraca', 'razoável', 'boa', 'forte']

/* ---------- componente ---------- */
export default function Register() {
    const [cpf, setCpf] = useState('')
    const [email, setEmail] = useState('')
    const [password, setPassword] = useState('')
    const [touched, setTouched] = useState({ cpf: false, email: false, password: false })
    const [submitted, setSubmitted] = useState(false)
    const [loading, setLoading] = useState(false)
    const [serverError, setServerError] = useState('')
    const [registered, setRegistered] = useState(false)
    const URL_API = "http://localhost:8080"

    const cpfError =
        (!touched.cpf && !submitted)
            ? ''
            : !cpf
                ? 'Informe seu CPF'
                : !isValidCpf(cpf)
                    ? 'CPF inválido. Confira os números'
                    : ''

    const emailError =
        (!touched.email && !submitted)
            ? ''
            : !email
                ? 'Informe seu e-mail'
                : !isValidEmail(email)
                    ? 'E-mail inválido'
                    : ''

    const passwordError =
        (!touched.password && !submitted)
            ? ''
            : !password
                ? 'Crie uma senha'
                : password.length < 8
                    ? 'Use pelo menos 8 caracteres'
                    : ''

    const score = passwordScore(password)

    async function handleSubmit(event) {
        event.preventDefault()
        setSubmitted(true)
        setServerError('')
        setTouched({ cpf: true, email: true, password: true })
        if (cpfError || emailError || passwordError || !cpf || !email || !password) {
            return
        }

        setLoading(true)
        try {
            const response = await fetch(`${URL_API}/auth/register`, {
                method: "POST",
                headers: { "Content-Type": "application/json" },
                body: JSON.stringify({ email, cpf, password })
            })

            const data = await response.json()

            if (response.ok && data.success) {
                setRegistered(true)
            } else {
                setServerError(JsonMessage[data.error.error] || 'Não foi possível criar sua conta. Tente novamente.')
            }
        } catch (err) {
            setServerError('Erro de conexão. Verifique sua internet e tente novamente.')
        } finally {
            setLoading(false)
        }
    }

    return (
        <div className="page">
            <div className="bg-grid" aria-hidden="true" />
            <div className="bg-glow" aria-hidden="true" />

            <main className="shell">
                <section className="card">
                    <h2 className="card__title">Crie a sua conta</h2>

                    <form className="form" onSubmit={handleSubmit} noValidate>
                        <div className="field">
                            <label htmlFor="cpf" className="field__label">CPF</label>
                            <input
                                id="cpf"
                                name="cpf"
                                type="text"
                                inputMode="numeric"
                                autoComplete="off"
                                placeholder="000.000.000-00"
                                value={cpf}
                                onChange={(e) => setCpf(onlyDigits(e.target.value))}
                                onBlur={() => setTouched((t) => ({ ...t, cpf: true }))}
                                aria-invalid={!!cpfError}
                                className={cpfError ? 'input input--err' : 'input'}
                            />
                            {cpfError && <p className="msg msg--err">{cpfError}</p>}
                        </div>

                        <div className="field">
                            <label htmlFor="email" className="field__label">E-mail</label>
                            <input
                                id="email"
                                name="email"
                                type="email"
                                autoComplete="email"
                                placeholder="voce@email.com"
                                value={email}
                                onChange={(e) => setEmail(e.target.value)}
                                onBlur={() => setTouched((t) => ({ ...t, email: true }))}
                                aria-invalid={!!emailError}
                                className={emailError ? 'input input--err' : 'input'}
                            />
                            {emailError && <p className="msg msg--err">{emailError}</p>}
                        </div>

                        <div className="field">
                            <label htmlFor="password" className="field__label">Senha</label>
                            <input
                                id="password"
                                name="password"
                                type="password"
                                autoComplete="new-password"
                                placeholder="••••••••••"
                                value={password}
                                onChange={(e) => setPassword(e.target.value)}
                                onBlur={() => setTouched((t) => ({ ...t, password: true }))}
                                aria-invalid={!!passwordError}
                                className={passwordError ? 'input input--err' : 'input'}
                            />

                            <div className="meter" aria-hidden="true">
                                {[0, 1, 2, 3].map((i) => (
                                    <span
                                        key={i}
                                        className="meter__seg"
                                        style={{
                                            background: i < score ? '#2EE6A8' : 'transparent',
                                            borderColor: i < score ? '#2EE6A8' : 'rgba(255,255,255,0.15)',
                                        }}
                                    />
                                ))}
                            </div>

                            {passwordError
                                ? <p className="msg msg--err">{passwordError}</p>
                                : score > 0 && <p className="msg">Força: {strengthLabel[score]}.</p>
                            }
                        </div>

                        {serverError && <p className="msg msg--err">{serverError}</p>}

                        <button type="submit" className="btn" disabled={loading}>
                            {loading ? 'Enviando...' : 'Abrir minha conta'}
                            <svg viewBox="0 0 24 24" aria-hidden="true" className="btn__icon">
                                <path d="M4 12h15M13 6l6 6-6 6" stroke="currentColor" strokeWidth="2" fill="none" />
                            </svg>
                        </button>

                        <p className="login-link">
                            Já tem conta? <Link to="/login">Entrar</Link>
                        </p>

                        <p className="legal">
                            Ao continuar você aceita os{' '}
                            <a href="#termos">termos de uso</a> e a{' '}
                            <a href="#privacidade">política de privacidade</a>.
                        </p>
                    </form>
                </section>
            </main>

            {registered && (
                <div className="modal-overlay" onClick={() => setRegistered(false)}>
                    <div className="modal" onClick={(e) => e.stopPropagation()}>
                        <button
                            type="button"
                            className="modal__close"
                            onClick={() => setRegistered(false)}
                            aria-label="Fechar"
                        >
                            <svg viewBox="0 0 24 24" fill="none" aria-hidden="true">
                                <path d="M6 6l12 12M18 6L6 18" stroke="currentColor" strokeWidth="2" strokeLinecap="round" />
                            </svg>
                        </button>

                        <div className="success">
                            <div className="success__icon">
                                <svg viewBox="0 0 24 24" fill="none" aria-hidden="true">
                                    <path d="M5 13l4 4L19 7" stroke="currentColor" strokeWidth="2" strokeLinecap="round" strokeLinejoin="round" />
                                </svg>
                            </div>
                            <h2 className="success__title">Verifique seu e-mail</h2>
                            <p className="success__text">
                                Enviamos um link de verificação para{' '}
                                <span className="success__email">{email}</span>.
                                <br />
                                Clique nele para ativar sua conta.
                            </p>
                        </div>
                    </div>
                </div>
            )}
        </div>
    )
}