export interface AuthResponse{
    
     status?: number
     message?: string
     timestamp?: string
     errors?: string
     token?: string
     bearer? : string
     username?: string
}

export interface User { 
    username: string; 
}