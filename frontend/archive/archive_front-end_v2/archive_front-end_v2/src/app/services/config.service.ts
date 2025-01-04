import { Injectable } from "@angular/core";
import { environment } from "../../environments/environment";

@Injectable({
    providedIn: 'root'
})
export class ConfigService {
    
    private getValue<T>(key: string, defaultValue: T): T {
        let value: T = (environment as any)[key];
        return value !== undefined ? value : defaultValue;
    }

    public getBoolean(key: string, defaultValue: boolean): boolean {
        return this.getValue<boolean>(key, defaultValue);
    }

    public getNumber(key: string, defaultValue: number): number {
        return this.getValue<number>(key, defaultValue);
    }

    public getString(key: string, defaultValue: string): string {
        return this.getValue<string>(key, defaultValue);
    }
}