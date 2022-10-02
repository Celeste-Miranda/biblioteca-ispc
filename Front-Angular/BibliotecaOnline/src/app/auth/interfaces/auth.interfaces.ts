export interface AuthResponse{
     ok?: boolean;
     status?: number
     message?: string
     timestamp?: string
     errors?: string
     token?: string
     bearer? : string
     username?: string
     role?: string
}

export interface User { 
    username: string; 
}