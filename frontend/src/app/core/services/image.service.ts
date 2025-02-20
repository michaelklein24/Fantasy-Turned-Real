import { Injectable } from '@angular/core';
import { ConfigService } from './config.service';

@Injectable({
  providedIn: 'root'
})
export class ImageService {

  constructor(private configService: ConfigService) { }

  /**
   * Generates the URL to an image based on the provided folder path and image name.
   * @param folderPath The folder path in the bucket (e.g., 'shows/survivor/season-1/contestants').
   * @param imageName The specific image name (e.g., 'contestant1.jpg').
   * @returns The full URL to the image in Cloudflare R2.
   */
  getImageUrl(folderPath: string, imageName: string): string {
    const baseUrl = this.configService.getString('r2BucketUrl');
    let imageUri = `${folderPath}:${imageName}`;
    // Encode the URL by replacing colons with %3A and any other special characters
    return baseUrl + "/" + encodeURIComponent(imageUri);
  }
  
}
