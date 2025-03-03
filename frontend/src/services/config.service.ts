import { Injectable } from '@angular/core';
import { environment } from '../environments/environment';

interface Environment {
  [key: string]: string | undefined; 
}

@Injectable({
  providedIn: 'root',
})
export class ConfigService {
  private env: Environment = environment;

  constructor() {}

  /**
   * Get a string value from the environment or config.
   * @param key The key of the string value to retrieve.
   * @param defaultValue The default value to return if the key is not found.
   * @returns The string value.
   */
  getString(key: string, defaultValue: string = ''): string {
    return this.getValue(key, 'string', defaultValue);
  }

  /**
   * Get a number value from the environment or config.
   * @param key The key of the number value to retrieve.
   * @param defaultValue The default value to return if the key is not found.
   * @returns The number value.
   */
  getNumber(key: string, defaultValue: number = 0): number {
    return this.getValue(key, 'number', defaultValue);
  }

  /**
   * Get a boolean value from the environment or config.
   * @param key The key of the boolean value to retrieve.
   * @param defaultValue The default value to return if the key is not found.
   * @returns The boolean value.
   */
  getBool(key: string, defaultValue: boolean = false): boolean {
    return this.getValue(key, 'boolean', defaultValue);
  }

  /**
   * Get a value and convert it to a specific type.
   * @param key The key of the value to retrieve.
   * @param type The type to convert the value to (e.g., 'string', 'number', 'boolean').
   * @param defaultValue The default value to return if the key is not found.
   * @returns The converted value.
   */
  private getValue<T>(key: string, type: 'string' | 'number' | 'boolean', defaultValue: T): T {
    const value = this.env[key]; // Access the environment value
    if (value === undefined || value === null) {
      return defaultValue;
    }

    switch (type) {
      case 'number':
        const numberValue = isNaN(Number(value)) ? defaultValue : Number(value);
        return numberValue as T;
      case 'boolean':
        const booleanValue = value.toLowerCase() === 'true'
        return booleanValue as T;
      case 'string':
      default:
        return String(value) as T;
    }
  }
}
